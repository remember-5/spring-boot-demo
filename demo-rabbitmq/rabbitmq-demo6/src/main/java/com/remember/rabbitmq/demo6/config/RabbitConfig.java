package com.remember.rabbitmq.demo6.config;

import com.remember.rabbitmq.demo6.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@Component
public class RabbitConfig {

    @Bean
    public Queue demo08Queue() {
        return QueueBuilder.durable(Demo08Message.QUEUE)// durable: 是否持久化
                .exclusive() // exclusive: 是否排它
                .autoDelete() // autoDelete: 是否自动删除
                .ttl(10 * 1000) // 设置队列里的默认过期时间为 10 秒
                .deadLetterExchange(Demo08Message.EXCHANGE)
                .deadLetterRoutingKey(Demo08Message.DELAY_ROUTING_KEY)
                .build();
    }


    @Bean
    public Queue demo08DelayQueue(){
        return new Queue(Demo08Message.DELAY_QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                true); // autoDelete: 是否自动删除
    }


    @Bean
    public DirectExchange demo08Exchange(){
        return new DirectExchange(Demo08Message.EXCHANGE,true,true);
    }


    @Bean
    public Binding demo08Binding(){
        return BindingBuilder.bind(demo08Queue()).to(demo08Exchange()).with(Demo08Message.ROUTING_KEY);
    }

    @Bean
    public Binding demo08DelayBinding(){
        return BindingBuilder.bind(demo08DelayQueue()).to(demo08Exchange()).with(Demo08Message.DELAY_ROUTING_KEY);
    }







}
