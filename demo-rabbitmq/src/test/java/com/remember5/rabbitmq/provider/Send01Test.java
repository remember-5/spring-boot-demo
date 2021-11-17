package com.remember5.rabbitmq.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@SpringBootTest
public class Send01Test implements Serializable {

    @Resource
    private Demo01Provider demo1Privider;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            // 创建 Demo01Message 消息
            demo1Privider.sendByExchangeAndRoutingKey("【sendByExchangeAndRoutingKey】 Hello RabbitMQ For Spring Boot!" + i);
            demo1Privider.sendByRoutingKey("【sendByRoutingKey】 Hello RabbitMQ For Spring Boot!" + i);
            demo1Privider.asyncSend("【asyncSend】 Hello RabbitMQ For Spring Boot!" + i);
        }
        new CountDownLatch(1).await();
    }

}
