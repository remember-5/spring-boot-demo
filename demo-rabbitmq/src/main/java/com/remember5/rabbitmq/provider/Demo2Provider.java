package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo2Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

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
     * 使用exchange + routingKey发送
     * 发送消息方法调用: 构建Message消息
     *
     * @param message    message
     * @param properties properties
     */
    public void send1(String msg) {
        Demo2Message message = new Demo2Message();
        message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo2Message.EXCHENGE, Demo2Message.KEY_1, message);
        log.info("[send1][发送消息：[{}] 发送成功]",message);

    }

    /**
     * 使用exchange + routingKey发送
     * 发送消息方法调用: 构建Message消息
     *
     * @param message    message
     * @param properties properties
     */
    public void send2(String msg) {
        Demo2Message message = new Demo2Message();
        message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo2Message.EXCHENGE, Demo2Message.KEY_2, message);
        log.info("[send2][发送消息：[{}] 发送成功]",message);
    }
}
