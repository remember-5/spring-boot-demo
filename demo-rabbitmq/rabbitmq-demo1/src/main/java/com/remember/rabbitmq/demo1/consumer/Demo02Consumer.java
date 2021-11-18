package com.remember.rabbitmq.demo1.consumer;

import com.remember.rabbitmq.demo1.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * TOPIC 解析routing key场景
 * 1.@Argument(name = "x-message-ttl",value = "3000") 绑定业务队列的时候，增加消息的过期时长，当消息过期后，消息将被转发到死信队列中。
 * 2.@Argument(name = "x-max-length",value = "3") 设置消息队列长度，当队列中的消息达到最大长度后，继续发送消息，消息将被转发到死信队列中
 * 3.@Argument(name = "x-dead-letter-exchange", value="exchange") 声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
 * 4.@Argument(name = "x-dead-letter-routing-key", value="routing-key") 声明该队列死信消息在交换机的 路由键
 * <p>
 * 消息被(basic.reject() or basic.nack()) and requeue = false，即消息被消费者拒绝或者nack，并且重新入队为false。、
 * nack()与reject()的区别是：reject()不支持批量拒绝，而nack()可以.
 *
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RabbitListener(
        bindings = @QueueBinding(
                // type: exchange 类型，
                exchange = @Exchange(value = Demo02Message.EXCHANGE, type = ExchangeTypes.TOPIC, durable = "true", autoDelete = "true"),
                // durable: 是否持久化  exclusive: 是否排它  autoDelete: 是否自动删除
                value = @Queue(value = Demo02Message.QUEUE, durable = "true", exclusive = "false", autoDelete = "true"),
                key = Demo02Message.ROUTING_KEY
        )
)
public class Demo02Consumer {


    /**
     * 监听普通消息
     *
     * @param message 具体的消息
     */
    @RabbitHandler
    public void onDeadMessage(Demo02Message message) throws IOException {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
