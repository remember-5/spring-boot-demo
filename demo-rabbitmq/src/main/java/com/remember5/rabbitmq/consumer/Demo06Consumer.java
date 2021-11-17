package com.remember5.rabbitmq.consumer;

import com.remember5.rabbitmq.message.Demo05Message;
import com.remember5.rabbitmq.message.Demo06Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo06Message.EXCHANGE, type = ExchangeTypes.DIRECT, durable = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo06Message.QUEUE, durable = "true", exclusive = "false", autoDelete = "false"),
                key = Demo06Message.ROUTING_KEY
        ),
        // 确认使用的工厂类
        containerFactory = "consumerBatchContainerFactory"
)
public class Demo06Consumer {


    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onMessage(List<Demo05Message> message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message.size());
    }
}
