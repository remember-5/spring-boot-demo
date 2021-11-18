package com.remember.rabbitmq.demo1.provider;

import com.remember.rabbitmq.demo1.message.Demo01Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Demo01Provider {

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
    public void sendByExchangeAndRoutingKey(String msg) {
        Demo01Message message = new Demo01Message();
        message.setMessage(msg);
        // 通过exchange和routingkey来直接发送消息
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY, message);
        log.info("[sendByExchangeAndRoutingKey][发送消息：[{}] 发送成功]", message);
    }

    /**
     * 使用queue发送
     *
     * @param message
     */
    public void sendByRoutingKey(String msg) {
        Demo01Message message = new Demo01Message();
        message.setMessage(msg);
        // 直接发送到queue上也可以
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE, message);
        log.info("[sendByRoutingKey][发送消息：[{}] 发送成功]", message);
    }

    /**
     * 异步消费
     *
     * @param message
     * @return
     */
    @Async
    public ListenableFuture<Void> asyncSend(String message) {
        try {
            // 发送消息
            this.sendByExchangeAndRoutingKey(message);
            // 返回成功的 Future
            log.info("[ListenableFuture][发送消息：[{}] 发送成功]", message);
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
