package com.remember5.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
//@Slf4j
//@Component
//@RabbitListener(bindings = {
//        @QueueBinding(
//                exchange = @Exchange(value = "deadExchange"),
//                value = @Queue(value = "deadQueue"),
//                key = "*"
//        )
//})
public class Demo10Consumer {
//    @RabbitHandler
//    public void deadMessage(String message, Channel channel, @Headers Map<String, Object> headers) {
//        log.info("死信: {}",message);
//    }
}
