package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo2Message;
import com.remember5.rabbitmq.message.Demo3Message;
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
public class Demo3Provider {

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
    public void send(String msg) {
        Demo3Message message = new Demo3Message();
        message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo3Message.EXCHENGE, null, message);
        log.info("[send1][发送消息：[{}] 发送成功]",message);
    }

}
