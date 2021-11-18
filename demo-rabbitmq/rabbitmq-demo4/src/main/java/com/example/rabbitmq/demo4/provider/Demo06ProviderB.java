package com.example.rabbitmq.demo4.provider;

import com.example.rabbitmq.demo4.message.Demo06MessageB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo06ProviderB {

    private final RabbitTemplate rabbitTemplatel;

    /**
     * 测试批量发送
     *
     * @param msg msg
     */
    public void send(String msg) {
        Demo06MessageB demo06Message = new Demo06MessageB();
        demo06Message.setMessage(msg);
        rabbitTemplatel.convertAndSend(Demo06MessageB.EXCHANGE, Demo06MessageB.ROUTING_KEY, demo06Message);
    }

}
