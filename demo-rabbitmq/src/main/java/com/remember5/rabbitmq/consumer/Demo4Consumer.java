package com.remember5.rabbitmq.consumer;

import com.remember5.rabbitmq.message.Demo3Message;
import com.remember5.rabbitmq.message.Demo4Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo4Message.EXCHENGE, type = ExchangeTypes.HEADERS, durable = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo4Message.QUEUE, durable = "true", exclusive = "false", autoDelete = "false")
        )
)
public class Demo4Consumer {
    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onMessage(Demo4Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
