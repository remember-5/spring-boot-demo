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
package com.remember.netty.mq.manager;

import com.remember.netty.mq.constant.RabbitConstants;
import com.remember.netty.mq.consumer.DynamicQueueListener;
import com.remember.netty.mq.consumer.OnceQueueListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author wangjiahao
 * @date 2023/12/20 21:09
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitmqManager {

    public static final String EXCHANGE_NAME = "netty.websocket";
    private static final DirectExchange DIRECT_EXCHANGE = new DirectExchange(EXCHANGE_NAME);

    private final RabbitAdmin rabbitAdmin;
    private final DynamicListenerManager dynamicListenerManager;


    /**
     * 初始化exchange
     */
    public void initExchange() {
        // 创建exchange
        rabbitAdmin.declareExchange(DIRECT_EXCHANGE);
    }


    /**
     * 创建queue
     *
     * @param instanceId
     */
    public void createQueue(String instanceId) {
        final String queueName = RabbitConstants.getInstanceQueueName(instanceId);
        final Queue queue = new Queue(queueName, true, false, false);
        final Binding binding = BindingBuilder.bind(queue).to(DIRECT_EXCHANGE).with(RabbitConstants.getInstanceRoutingKeyName(instanceId));

        // 创建queue
        rabbitAdmin.declareQueue(queue);
        // 绑定queue
        rabbitAdmin.declareBinding(binding);
        // 监听queue
        dynamicListenerManager.createListenerContainer(new DynamicQueueListener(), AcknowledgeMode.MANUAL, queueName);
    }


    public void createOnceQueue(String messageId) {
        final String queueName = RabbitConstants.getReplyQueueName(messageId);
        final Queue queue = new Queue(queueName, true, false, false);
        final Binding binding = BindingBuilder.bind(queue).to(DIRECT_EXCHANGE).with(RabbitConstants.getReplyRoutingKeyName(messageId));

        // 创建queue
        rabbitAdmin.declareQueue(queue);
        // 绑定queue
        rabbitAdmin.declareBinding(binding);
        // 监听queue
        dynamicListenerManager.createListenerContainer(new OnceQueueListener(), AcknowledgeMode.MANUAL, queueName);
    }

}
