package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo05Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo05Provider {

    private final BatchingRabbitTemplate rabbitTemplatel;

    /**
     * 测试批量发送
     *
     * @param msg msg
     */
    public void send(String msg) {
        Demo05Message demo05Message = new Demo05Message();
        demo05Message.setMessage(msg);
        rabbitTemplatel.convertAndSend(Demo05Message.EXCHANGE, Demo05Message.ROUTING_KEY, demo05Message);
    }

}
