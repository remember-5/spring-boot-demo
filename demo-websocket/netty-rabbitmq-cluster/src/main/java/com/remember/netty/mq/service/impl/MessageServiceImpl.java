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
package com.remember.netty.mq.service.impl;

import com.alibaba.fastjson.JSON;
import com.remember.netty.mq.constant.RabbitConstants;
import com.remember.netty.mq.entity.RabbitmqMessage;
import com.remember.netty.mq.manager.NettyChannelManager;
import com.remember.netty.mq.manager.RabbitmqManager;
import com.remember.netty.mq.service.MessageService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author wangjiahao
 * @date 2023/12/20 21:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    public final RabbitTemplate rabbitTemplate;
    private final RabbitmqManager rabbitmqManager;
    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    @Override
    public Mono<String> sendLocalMessage(RabbitmqMessage rabbitmqMessage) {
        return Mono.create(sink -> {
            final String userId = rabbitmqMessage.getUserId();
            // 本地获取
            if (NettyChannelManager.getUserChannelMap().containsKey(userId)) {
                final Channel channel = NettyChannelManager.getUserChannelMap().get(userId);
                final ChannelFuture channelFuture = channel.writeAndFlush(new TextWebSocketFrame(rabbitmqMessage.getMessage()));
                channelFuture.addListener(future -> {
                    if (future.isSuccess()) {
                        // 异步操作成功
                        sink.success();
                    } else {
                        // 异步操作失败
                        sink.error(future.cause());
                    }
                });
            } else {
                sink.error(new RuntimeException("用户不在线"));
            }
        });
    }

    @Override
    public Mono<String> sendMessage(RabbitmqMessage rabbitmqMessage) {
        // 创建一次性消费者
        final String messageId = rabbitmqMessage.getMessageId();
//        rabbitmqManager.createOnceQueue(messageId);

        // 发送消息到给用户mq
        rabbitTemplate.convertAndSend(RabbitmqManager.EXCHANGE_NAME,
                RabbitConstants.getInstanceRoutingKeyName(rabbitmqMessage.getUserId()),
                JSON.toJSONString(rabbitmqMessage));

        return reactiveRedisTemplate
                // 监听Redis通道
                .listenToChannel(messageId)
                // 获取第一条符合条件的消息
                .next()
                // 从Redis中获取对应的值
                .flatMap(message -> Mono.just(message.getMessage().toString()));
    }

    @Override
    public void test() {
    }
}
