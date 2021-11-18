package com.example.rabbitmq.demo2.config;

import com.example.rabbitmq.demo2.consumer.Demo05Consumer;
import com.example.rabbitmq.demo2.message.Demo05Message;
import com.example.rabbitmq.demo2.provider.Demo05Provider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
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
public class Demo05Config {

    /**
     * 用于rabbitmq的批量发送，这里利用的是超时等待，批量收集
     * 具体的 batchSize、bufferLimit、timeout 数值配置多少，根据自己的应用来。这里，我们故意将 timeout 配置成了 30 秒，主要为了演示之用。
     * 适用于
     *
     * @param connectionFactory
     * @return
     * @see Demo05Message
     * @see Demo05Provider
     * @see Demo05Consumer
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
}
