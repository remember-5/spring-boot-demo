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

import com.remember.netty.mq.consumer.DynamicQueueListener;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

/**
 * 动态queue监听管理
 *
 * @author wangjiahao
 * @date 2023/12/19 17:45
 */
@RequiredArgsConstructor
public class DynamicListenerManager {

    private final SimpleMessageListenerContainer simpleMessageListenerContainer;

    public void addListenerForQueue(String queueName) {
        // 创建消息监听器
        DynamicQueueListener listener = new DynamicQueueListener();

        // 动态添加要监听的队列
        simpleMessageListenerContainer.addQueueNames(queueName);
        // 设置消息监听器
        simpleMessageListenerContainer.setMessageListener(listener);
        // 设置ack模式
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 重新启动消息监听器容器，使其生效
        simpleMessageListenerContainer.start();
    }

}
