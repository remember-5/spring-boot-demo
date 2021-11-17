package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo05Message;
import com.remember5.rabbitmq.message.Demo06Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo06Provider {

    private final BatchingRabbitTemplate rabbitTemplatel;

    /**
     * 测试批量发送
     *
     * @param msg msg
     */
    public void send(String msg) {
        Demo06Message demo06Message = new Demo06Message();
        demo06Message.setMessage(msg);
        rabbitTemplatel.convertAndSend(Demo06Message.EXCHANGE, Demo06Message.ROUTING_KEY, demo06Message);
    }

}
