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
package com.remember.netty.mq.config;

import com.remember.netty.mq.manager.DynamicListenerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 *
 * @author wangjiahao
 * @date 2023/12/19 15:12
 */
@Slf4j
@Configuration
@EnableRabbit
public class RabbitmqConfig {

    /**
     * 开启rabbitAdmin
     *
     * @param connectionFactory /
     * @return rabbitAdmin
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


    /**
     * 消费者监听器
     *
     * @param connectionFactory /
     * @return simpleMessageListenerContainer
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }

    /**
     * 动态消费监听管理器
     *
     * @param simpleMessageListenerContainer /
     * @return dynamicListenerManager
     */
    @Bean
    @ConditionalOnBean(SimpleMessageListenerContainer.class)
    public DynamicListenerManager dynamicListenerManager(SimpleMessageListenerContainer simpleMessageListenerContainer) {
        return new DynamicListenerManager(simpleMessageListenerContainer);
    }


}
