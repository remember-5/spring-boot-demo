package com.remember5.rabbitmq.consumer;

import com.remember5.rabbitmq.message.Demo01Message;
import com.remember5.rabbitmq.provider.Demo01Provider;
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
                exchange = @Exchange(value = Demo01Message.EXCHANGE, type = ExchangeTypes.DIRECT, durable = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo01Message.QUEUE, durable = "true", exclusive = "false", autoDelete = "false"),
                key = Demo01Message.ROUTING_KEY
        )
)
public class Demo01Consumer {

    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onMessage(Demo01Provider message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
