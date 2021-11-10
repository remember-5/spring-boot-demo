package com.remember5.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.remember.common.rabbitmq.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
//@Slf4j
//@Component
//@RabbitListener(bindings = @QueueBinding(
//        value = @Queue(value = "queue-2", durable = "true"),
//        exchange = @Exchange(value = "exchange-2", type = "topic", ignoreDeclarationExceptions = "true"),
//        key = "springboot.*"
//))
public class Demo3Consumer {
//    @RabbitHandler
//    public void onOrderMessage(Order order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
//        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
//        try {
//            log.info("消费端order: {} ", order);
//            //手工ACK
//            channel.basicAck(deliveryTag, false);
//        } catch (Exception e) {
//            //否则不ACK
//            channel.basicNack(deliveryTag, false, true);
//        }
//    }

}
