package com.example.rabbitmq.demo5.provider;

import com.example.rabbitmq.demo5.message.Demo07Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo07Provider {
    private final RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        Demo07Message demo07Message = new Demo07Message();
        demo07Message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo07Message.EXCHANGE, Demo07Message.ROUTING_KEY, demo07Message);
    }


}
