package com.remember.websocket.route.provider;

import com.remember.websocket.route.entity.Demo02Message;
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
public class Demo02Provider {

    /**
     * 自动注入RabbitTemplate模板类
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * 使用exchange + routingKey发送
     * 发送消息方法调用: 构建Message消息
     *
     * @param msg    message
     */
    public void send1(String msg) {
        Demo02Message message = new Demo02Message();
        message.setMessage(msg);
        // 根据定义到规则，解析不同到routing key
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, Demo02Message.ROUTING_KEY, message);
        log.info("[send1][发送消息：[{}] 发送成功]",message);

    }

    /**
     * 使用exchange + routingKey发送
     * 发送消息方法调用: 构建Message消息
     *
     * @param msg    message
     */
    public void send2(String msg) {
        Demo02Message message = new Demo02Message();
        message.setMessage(msg);
        // 根据定义到规则，解析不同到routing key
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, Demo02Message.ROUTING_KEY, message);
        log.info("[send2][发送消息：[{}] 发送成功]",message);
    }
}
