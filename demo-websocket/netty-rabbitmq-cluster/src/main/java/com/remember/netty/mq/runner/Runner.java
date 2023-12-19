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
package com.remember.netty.mq.runner;

import com.remember.netty.mq.manager.DynamicListenerManager;
import com.remember.netty.mq.constant.RabbitRoutingKeyConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2023/12/19 15:35
 */
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private static final String EXCHANGE_NAME = "netty.websocket";
    private final RabbitAdmin rabbitAdmin;
    private final RabbitTemplate rabbitTemplate;
    private final DynamicListenerManager dynamicListenerManager;


    @Override
    public void run(String... args) throws Exception {
        // 创建exchange
        final DirectExchange topicExchange = new DirectExchange(EXCHANGE_NAME);
        rabbitAdmin.declareExchange(topicExchange);

        String instanceNameA = "a1234";
        String instanceNameB = "b1234";

        // 创建queue
        final Queue queueA = new Queue(instanceNameA, true, false, false);
        final Queue queueB = new Queue(instanceNameB, true, false, false);
        rabbitAdmin.declareQueue(queueA);
        rabbitAdmin.declareQueue(queueB);

        // 监听queue
        dynamicListenerManager.addListenerForQueue(instanceNameA);
        dynamicListenerManager.addListenerForQueue(instanceNameB);


        // 绑定queue
        Binding bindingA = BindingBuilder.bind(queueA).to(topicExchange).with(RabbitRoutingKeyConstants.getPrefixConsumer(instanceNameA));
        Binding bindingB = BindingBuilder.bind(queueB).to(topicExchange).with(RabbitRoutingKeyConstants.getPrefixConsumer(instanceNameB));
        rabbitAdmin.declareBinding(bindingA);
        rabbitAdmin.declareBinding(bindingB);
    }


}
