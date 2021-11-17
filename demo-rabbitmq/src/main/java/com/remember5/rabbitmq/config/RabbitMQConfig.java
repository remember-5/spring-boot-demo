package com.remember5.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
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


    /**
     * 用于rabbitmq的批量发送，这里利用的是超时等待，批量收集
     * 具体的 batchSize、bufferLimit、timeout 数值配置多少，根据自己的应用来。这里，我们故意将 timeout 配置成了 30 秒，主要为了演示之用。
     * 适用于
     *
     * @param connectionFactory
     * @return
     * @see com.remember5.rabbitmq.message.Demo05Message
     * @see com.remember5.rabbitmq.provider.Demo05Provide
     * @see com.remember5.rabbitmq.consumer.Demo05Consumer
     */
    @Bean(name = "batchingRabbitTemplate")
    public BatchingRabbitTemplate batchingRabbitTemplate(ConnectionFactory connectionFactory) {
        //TODO 这其实可以写到环境变量里，不知道有没有现成的实现

        // 创建 BatchingStrategy 对象，代表批量策略
        int batchSize = 16348; // 超过收集的消息数量的最大条数
        int bufferLimit = 33554432; // 每次批量发送消息的最大内存
        int timeout = 30000;// 超时收集的时间的最大等待时长，单位: 毫秒
        BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);

        // 创建 TaskScheduler 对象，用于实现超时发送的定时器
        TaskScheduler taskScheduler = new ConcurrentTaskScheduler();

        // 创建 BatchingRabbitTemplate 对象
        BatchingRabbitTemplate batchingRabbitTemplate = new BatchingRabbitTemplate(batchingStrategy, taskScheduler);
        batchingRabbitTemplate.setConnectionFactory(connectionFactory);
        return batchingRabbitTemplate;
    }

    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // 额外添加批量消费的属性
        factory.setBatchListener(true);
        return factory;
    }


}

