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

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

/**
 * @author wangjiahao
 * @date 2023/12/20 22:47
 */
@Slf4j
public class OnceQueueListener implements ChannelAwareMessageListener {
    private final MonoProcessor<String> messageProcessor = MonoProcessor.create();

    public Mono<Void> onMessageProcessed() {
        return messageProcessor.then();
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody());
        log.info("OnceQueueListener Received message: " + msg);
        // 将消息发送到 MonoProcessor
        messageProcessor.onNext(msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
