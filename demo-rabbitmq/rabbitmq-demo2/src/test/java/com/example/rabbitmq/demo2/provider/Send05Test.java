package com.example.rabbitmq.demo2.provider;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Slf4j
@SpringBootTest
public class Send05Test {

    @Autowired
    Demo05Provider demo05Provider;

    @Test
    void send() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            demo05Provider.send("【send】 Hello RabbitMQ For Spring Boot! " + i);
            log.info("[send][发送编号：[{}] 发送成功]",i);
            Thread.sleep(10000);
        }
        new CountDownLatch(1).await();
        
    }


}
