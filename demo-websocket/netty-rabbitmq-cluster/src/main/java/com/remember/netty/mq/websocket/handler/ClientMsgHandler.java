/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.netty.mq.websocket.handler;

import com.alibaba.fastjson.JSONObject;
import com.remember.netty.mq.constant.RabbitConstants;
import com.remember.netty.mq.entity.RabbitmqMessage;
import com.remember.netty.mq.manager.NettyChannelManager;
import com.remember.netty.mq.manager.RabbitmqManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 接收客户端消息的handler
 *
 * @author wangjiahao
 * @date 2022/12/13 17:30
 */
@Slf4j
@Component
@ChannelHandler.Sharable
@RequiredArgsConstructor
public class ClientMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate rabbitTemplate;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        log.info("服务器收到消息：{}", msg.text());
        final RabbitmqMessage javaObject = JSONObject.toJavaObject(JSONObject.parseObject(msg.text()), RabbitmqMessage.class);
        // 将消息发送到 MQ

        rabbitTemplate.convertAndSend(RabbitmqManager.EXCHANGE_NAME,
                RabbitConstants.getReplyRoutingKeyName(javaObject.getMessageId()),
                msg.text());
        // 回复消息
//        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器连接成功！"));
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用" + ctx.channel().id().asLongText());
        removeUserId(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常：{}", cause.getMessage());
        removeUserId(ctx);
        ctx.close();
    }


    private void removeUserId(ChannelHandlerContext ctx) {
        NettyChannelManager.getChannelGroup().remove(ctx.channel());

        AttributeKey<String> key = AttributeKey.valueOf("userId");
        String userId = ctx.channel().attr(key).get();
        NettyChannelManager.getUserChannelMap().remove(userId);
        if (Boolean.TRUE.equals(redisTemplate.hasKey(RabbitConstants.WS_CLIENT + RabbitConstants.ADDRESS_MD5))) {
            redisTemplate.opsForSet().remove(RabbitConstants.WS_CLIENT + RabbitConstants.ADDRESS_MD5, userId);
        }
        ctx.channel().close();
    }
}
