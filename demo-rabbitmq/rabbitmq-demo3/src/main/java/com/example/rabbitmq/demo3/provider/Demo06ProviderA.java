package com.example.rabbitmq.demo3.provider;

import com.example.rabbitmq.demo3.message.Demo06MessageA;
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
public class Demo06ProviderA {

    private final BatchingRabbitTemplate rabbitTemplate;

    /**
     * 测试批量发送
     *
     * @param msg msg
     */
    public void send(String msg) {
        Demo06MessageA demo06Message = new Demo06MessageA();
        demo06Message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo06MessageA.EXCHANGE, Demo06MessageA.ROUTING_KEY, demo06Message);
    }

}
