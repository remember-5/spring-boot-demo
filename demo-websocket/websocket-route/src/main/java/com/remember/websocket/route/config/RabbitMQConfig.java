package com.remember.websocket.route.config;


import com.remember.websocket.route.entity.Demo02Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2021/12/17
 */
@Configuration
public class RabbitMQConfig {

    // 创建 Queue
    @Bean
    public Queue demo02Queue() {
        return new Queue(Demo02Message.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }

    // 创建 Topic Exchange
    @Bean
    public TopicExchange demo02Exchange() {
        return new TopicExchange(Demo02Message.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Binding
    // Exchange：Demo02Message.EXCHANGE
    // Routing key：Demo02Message.ROUTING_KEY
    // Queue：Demo02Message.QUEUE
    @Bean
    public Binding demo02Binding() {
        return BindingBuilder.bind(demo02Queue()).to(demo02Exchange()).with(Demo02Message.ROUTING_KEY);
    }
}
