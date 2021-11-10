package com.remember5.rabbitmq.provider;

import com.remember.common.rabbitmq.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo2Provider {

    /**
     * 自动注入RabbitTemplate模板类
     */
    private final RabbitTemplate rabbitTemplate;
    /**
     * 发送消息方法调用: 构建自定义对象消息
     * 消费者必须要用同一个对象，否则失败
     *
     * @param order 消息
     */
    public void sendOrder(Order order) {
        //id + 时间戳 全局唯一
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend("exchange-2", "springboot.def", order, correlationData);
    }
}
