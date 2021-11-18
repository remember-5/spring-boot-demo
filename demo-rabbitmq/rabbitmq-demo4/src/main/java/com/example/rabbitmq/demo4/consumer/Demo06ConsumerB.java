package com.example.rabbitmq.demo4.consumer;

import com.example.rabbitmq.demo4.message.Demo06MessageB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 工厂类记得选择
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo06MessageB.EXCHANGE, type = ExchangeTypes.DIRECT, durable = "true", autoDelete = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo06MessageB.QUEUE, durable = "true", exclusive = "false", autoDelete = "true"),
                key = Demo06MessageB.ROUTING_KEY
        ),
        // 确认使用的工厂类
        containerFactory = "consumerBatchContainerFactory"
)
public class Demo06ConsumerB {


    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onMessage(List<Demo06MessageB> message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message.size());
    }
}
