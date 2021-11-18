package com.remember.rabbitmq.demo1.consumer;

import com.remember.rabbitmq.demo1.message.Demo03Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * FANOUT模式，找到不同到queue，不同到QUEUE都会消费
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo03Message.EXCHANGE, type = ExchangeTypes.FANOUT, durable = "true", autoDelete = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo03Message.QUEUE_B, durable = "true", exclusive = "false", autoDelete = "true")
        )
)
public class Demo03ConsumerB {

    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onDeadMessage(Demo03Message message) throws IOException {
        log.info("[Demo3ConsumerB-onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
