package com.remember.rabbitmq.consumer.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.remember.rabbitmq.consumer.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2020/3/16
 */
@Slf4j
@Component
public class RabbitReceiver {

    @RabbitListener(
            bindings = @QueueBinding(
            value = @Queue(value = "queue-1", durable = "true"),
            exchange = @Exchange(value = "exchange-1", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "springboot.*")
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Order order = JSONObject.toJavaObject(JSON.parseObject(message.getPayload().toString()), Order.class);
        log.info("{}",order);
        MessageHeaders headers = message.getHeaders();
        System.err.println(headers);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }


    /**
     * spring.rabbitmq.listener.order.queue.name=queue-2
     * spring.rabbitmq.listener.order.queue.durable=true
     * spring.rabbitmq.listener.order.exchange.name=exchange-1
     * spring.rabbitmq.listener.order.exchange.durable=true
     * spring.rabbitmq.listener.order.exchange.type=topic
     * spring.rabbitmq.listener.order.exchange.ignoreDeclarationExceptions=true
     * spring.rabbitmq.listener.order.key=springboot.*
     *
     * @param order
     * @param channel
     * @param headers
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue-2", durable = "true"),
            exchange = @Exchange(value = "exchange-2", type = "topic", ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    ))
    @RabbitHandler
    public void onOrderMessage(Order order, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        System.err.println("onOrderMessage");
        System.err.println("--------------------------------------");
        System.err.println("消费端order: " + order.getOrderId());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
