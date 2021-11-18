package com.example.rabbitmq.demo3.config;

import com.example.rabbitmq.demo3.consumer.Demo06ConsumerA;
import com.example.rabbitmq.demo3.message.Demo06MessageA;
import com.example.rabbitmq.demo3.provider.Demo06ProviderA;
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
 * @date 2021/11/18
 */
@Slf4j
@Component
public class Demo06ConfigA {


    /**
     * 用于rabbitmq的批量发送，这里利用的是超时等待，批量收集
     * 具体的 batchSize、bufferLimit、timeout 数值配置多少，根据自己的应用来。这里，我们故意将 timeout 配置成了 30 秒，主要为了演示之用。
     * 适用于
     *
     * @param connectionFactory
     * @return
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

    /**
     * 批量消费，这个只是批量接收消息结合上面这个使用。超时则消费，不算完全的一个批量消费，下面是扩展版本
     *
     * @param configurer        /
     * @param connectionFactory /
     * @return /
     * @see Demo06ConsumerA
     * @see Demo06ProviderA
     * @see Demo06MessageA
     */
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
