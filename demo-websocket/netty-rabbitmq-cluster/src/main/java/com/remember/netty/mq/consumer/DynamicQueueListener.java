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
package com.remember.netty.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.remember.netty.mq.entity.RabbitmqMessage;
import com.remember.netty.mq.manager.NettyChannelManager;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * 用于接收消息
 *
 * @author wangjiahao
 * @date 2023/12/19 17:29
 */
@Slf4j
public class DynamicQueueListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        final String msg = new String(message.getBody());
        log.info("DynamicQueueListener Received message from dynamic queue: " + msg);
        final RabbitmqMessage javaObject = JSON.toJavaObject(JSON.parseObject(msg), RabbitmqMessage.class);
        if (NettyChannelManager.getUserChannelMap().containsKey(javaObject.getUserId())) {
            NettyChannelManager.getUserChannelMap().get(javaObject.getUserId()).writeAndFlush(new TextWebSocketFrame(javaObject.getMessage()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
