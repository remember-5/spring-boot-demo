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

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态消费监听管理器
 *
 * @author wangjiahao
 * @date 2023/12/19 17:45
 */
@Component
@RequiredArgsConstructor
public class DynamicListenerManager {

    private final RabbitAdmin rabbitAdmin;

    private ConcurrentHashMap<String, MessageListener> listenerMap = new ConcurrentHashMap<>();

    public SimpleMessageListenerContainer createListenerContainer(MessageListener listener, AcknowledgeMode acknowledgeMode, String... queueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitAdmin.getRabbitTemplate().getConnectionFactory());

        // 动态添加要监听的队列
        container.addQueueNames(queueName);
        // 设置消息监听器
        container.setMessageListener(listener);
        // 设置ack模式
        container.setAcknowledgeMode(acknowledgeMode);
        // 重新启动消息监听器容器，使其生效
        container.start();

        return container;
    }


    /**
     * 获取消费者
     *
     * @param queueName
     */
    public <T extends ChannelAwareMessageListener> T getListenerForQueue(String queueName, Class<T> listenerClass ) {
        MessageListener listener = listenerMap.get(queueName);
        if (listenerClass.isInstance(listener)) {
            return listenerClass.cast(listener);
        }
        return null;
    }
    }
