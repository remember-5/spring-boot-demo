package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo04Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo04Provider {

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
    public void send(String msg, String headerValue) {
        // 创建header
        MessageProperties properties = new MessageProperties();
        properties.setHeader(Demo04Message.HEADER_KEY, headerValue);
        // 创建message
        Demo04Message demo04Message = new Demo04Message();
        demo04Message.setMessage(msg);
        // 转换
        Message message = rabbitTemplate.getMessageConverter().toMessage(demo04Message, properties);
        rabbitTemplate.convertAndSend(Demo04Message.EXCHANGE, null, message);
        log.info("[send][发送消息：[{}] 发送成功]", message);
    }

}
