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


    /**
     * 1.@Argument(name = "x-message-ttl",value = "3000") 绑定业务队列的时候，增加消息的过期时长，当消息过期后，消息将被转发到死信队列中。
     * 2.@Argument(name = "x-max-length",value = "3") 设置消息队列长度，当队列中的消息达到最大长度后，继续发送消息，消息将被转发到死信队列中
     * @param message
     * @param channel
     * @throws Exception
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue-1", durable = "true"),
                    exchange = @Exchange(value = "exchange-1", type = "topic", ignoreDeclarationExceptions = "true", delayed = "true",arguments =
                        {
                            @Argument(name="x-dead-letter-exchange",value = "deadExchange"),
                            @Argument(name="x-dead-letter-routing-key",value = "deadKey"),
                        }
                    ),
                    key = "springboot.*")
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        try {
            Order order = JSONObject.toJavaObject(JSON.parseObject(message.getPayload().toString()), Order.class);
            log.info("{}", order);
            if ("D001".equals(order.getOrderId())) {
//                return;
                channel.basicNack(deliveryTag, false, false);
                return;
            }
            MessageHeaders headers = message.getHeaders();
            System.err.println(headers);
            //手工ACK
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
//            channel.basicNack(deliveryTag, false, true);
            channel.basicNack(deliveryTag, false, false);
        }

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
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            System.err.println("onOrderMessage");
            System.err.println("--------------------------------------");
            System.err.println("消费端order: " + order.getOrderId());
            //手工ACK
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            //否则不ACK
            channel.basicNack(deliveryTag, false, true);
        }
    }


    /**
     *
     * @param message
     * @param channel
     * @param headers
     */
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "javatripDead"),
                    exchange = @Exchange(value = "deadExchange"),
                    key = "deadKey"
            )
    })
    public void deadMessage(String message, Channel channel, @Headers Map<String, Object> headers) {
        System.out.println("死信" + message);
    }


}
