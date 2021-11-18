package com.example.rabbitmq.demo4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@Component
public class Demo06ConfigB {


    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory
    ){

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory,connectionFactory);
        // 添加批量消费的属性
        factory.setBatchListener(true);

        // 设置其他属性
        factory.setBatchSize(10);
        factory.setReceiveTimeout(30 * 1000L);
        factory.setConsumerBatchEnabled(true);

        return factory;
    }

}
