package com.example.rabbitmq.demo5.config;

import com.example.rabbitmq.demo5.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
public class RabbitMQConfig {

    /**
     * 这里暂时没用到
     */
//    @Bean(name = "rabbitTemplate")
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
//        connectionFactory.setPublisherConfirms(true);
//        connectionFactory.setPublisherReturns(true);
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        // 序列化
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
//        return rabbitTemplate;
//    }


    // 这次让我们来试试不一样的创建队列的方式
    @Bean
    public Queue demo07Queue() {
        return QueueBuilder.durable(Demo07Message.QUEUE) // durable 是否持久化
                .exclusive() // exclusive: 是否排它
                .autoDelete() // autoDelete: 是否自动删除
                .deadLetterExchange(Demo07Message.EXCHANGE)
                .deadLetterRoutingKey(Demo07Message.DEAD_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue demo07DeadQueue() {
        return new Queue(Demo07Message.DEAD_QUEUE, // Queue 名字
                true, // durable 是否持久化
                false, //exclusive 是否排它
                false // 是否自动删除
        );
    }

    @Bean
    public DirectExchange demo07DirectExchange() {
        return new DirectExchange(
                Demo07Message.EXCHANGE,
                true, // durable: 是否持久化
                true
        );
    }

    @Bean
    public Binding demo07Binding(){
        return BindingBuilder.bind(demo07Queue()).to(demo07DirectExchange()).with(Demo07Message.ROUTING_KEY);
    }


    @Bean
    public Binding demo07DeadBinding(){
        return BindingBuilder.bind(demo07DeadQueue()).to(demo07DirectExchange()).with(Demo07Message.DEAD_ROUTING_KEY);
    }





}

