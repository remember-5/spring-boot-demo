package com.remember5.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 1.@Argument(name = "x-message-ttl",value = "3000") 绑定业务队列的时候，增加消息的过期时长，当消息过期后，消息将被转发到死信队列中。
 * 2.@Argument(name = "x-max-length",value = "3") 设置消息队列长度，当队列中的消息达到最大长度后，继续发送消息，消息将被转发到死信队列中
 * 3.@Argument(name = "x-dead-letter-exchange", value="exchange") 声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
 * 4.@Argument(name = "x-dead-letter-routing-key", value="routing-key") 声明该队列死信消息在交换机的 路由键
 * <p>
 * 消息被(basic.reject() or basic.nack()) and requeue = false，即消息被消费者拒绝或者nack，并且重新入队为false。、
 * nack()与reject()的区别是：reject()不支持批量拒绝，而nack()可以.
 * @author wangjiahao
 * @date 2021/11/10
 */
//@Slf4j
//@Component
//@RabbitListener(
//        bindings = @QueueBinding(
//                exchange = @Exchange(value = "exchange-1", type = "topic"),
//                value = @Queue(value = "queue-1", durable = "true"),
//                key = "dead.*",
//                arguments = {
//                        @Argument(name = "x-message-ttl", value = "2000"),
//                        @Argument(name = "x-dead-letter-exchange", value = "deadExchange"),
//                        @Argument(name = "x-dead-letter-routing-key", value = "deadQueue")
//                }
//        )
//)
public class Demo2Consumer {

//    @RabbitHandler
//    public void onDeadMessage(Message message, Channel channel) throws IOException {
//        log.info("--------------------------------------");
//        log.info("消费端Payload: {}", message.getPayload());
//        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
//        try {
//            //手工ACK
////            channel.basicAck(deliveryTag, true);
//            channel.basicNack(deliveryTag, false, false);
//        } catch (Exception e) {
//        }
//    }
}
