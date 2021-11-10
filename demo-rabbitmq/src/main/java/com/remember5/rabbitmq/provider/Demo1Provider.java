package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo1Message;
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
public class Demo1Provider {

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
    public void sendByExchangeAndRoutingKey(String message) {
        rabbitTemplate.convertAndSend(Demo1Message.EXCHENGE, Demo1Message.ROUTING_KEY, message);
    }

    /**
     * 使用queue发送
     *
     * @param message
     */
    public void sendByRoutingKey(String message) {
        rabbitTemplate.convertAndSend(Demo1Message.QUEUE, message);
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
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
