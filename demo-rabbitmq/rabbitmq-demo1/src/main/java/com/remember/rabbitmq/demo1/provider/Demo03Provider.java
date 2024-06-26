package com.remember.rabbitmq.demo1.provider;

import com.remember.rabbitmq.demo1.message.Demo03Message;
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
public class Demo03Provider {

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
        Demo03Message message = new Demo03Message();
        message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo03Message.EXCHANGE, null, message);
        log.info("[send1][发送消息：[{}] 发送成功]",message);
    }

}
