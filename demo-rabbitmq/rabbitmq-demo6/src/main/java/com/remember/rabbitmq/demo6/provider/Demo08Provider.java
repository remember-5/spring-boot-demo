package com.remember.rabbitmq.demo6.provider;

import com.remember.rabbitmq.demo6.message.Demo08Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo08Provider {
    private final RabbitTemplate rabbitTemplate;

    public void send(String msg, Integer delay) {
        Demo08Message demo08Message = new Demo08Message();
        demo08Message.setMessage(msg);
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, demo08Message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
               if(delay != null && delay > 0){
                   // Spring-AMQP API 设计有问题，所以传入了 String = =
                   message.getMessageProperties().setExpiration(String.valueOf(delay));
               }
               return message;
            }
        });

    }
}
