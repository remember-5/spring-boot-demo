package com.example.rabbitmq.demo5.consumer;

import com.example.rabbitmq.demo5.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@Component
@RabbitListener(queues = Demo07Message.QUEUE)
public class Demo07Consumer {

    @RabbitHandler
    public void onMessage(Demo07Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        // <X> 注意，此处抛出一个 RuntimeException 异常，模拟消费失败
        throw new RuntimeException("我就是故意抛出一个异常");
    }


}
