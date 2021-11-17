package com.remember5.rabbitmq.consumer;

import com.remember5.rabbitmq.message.Demo1Message;
import com.remember5.rabbitmq.provider.Demo1Provider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * String消息接收
 *
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo1Message.EXCHENGE, type = ExchangeTypes.DIRECT, durable = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo1Message.QUEUE, durable = "true", exclusive = "false", autoDelete = "false"),
                key = Demo1Message.ROUTING_KEY
        )
)
public class Demo1Consumer {

    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onMessage(Demo1Provider message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
