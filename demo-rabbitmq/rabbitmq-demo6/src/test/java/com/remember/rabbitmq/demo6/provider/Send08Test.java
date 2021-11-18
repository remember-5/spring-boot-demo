package com.remember.rabbitmq.demo6.provider;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangjiahao
 * @date 2021/11/18
 */
@Slf4j
@SpringBootTest
public class Send08Test {

    @Autowired
    Demo08Provider demo08Provider;


    @Test
    void send() throws InterruptedException {
        this.testSend(null);

    }
    @Test
    void send1() throws InterruptedException {
        this.testSend(5000);
    }


    private void testSend(Integer delay) throws InterruptedException {
        demo08Provider.send("【send】 Hello RabbitMQ For Spring Boot! ",delay);
        log.info("[testSyncSendDelay][发送编号：[{}] 发送成功]",1);
        new CountDownLatch(1).await();
    }

}
