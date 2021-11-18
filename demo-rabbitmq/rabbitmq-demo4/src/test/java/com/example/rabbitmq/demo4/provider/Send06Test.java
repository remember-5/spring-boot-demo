package com.example.rabbitmq.demo4.provider;

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
public class Send06Test {

    @Autowired
    Demo06ProviderB demo06ProviderlB;


    @Test
    void send1() throws InterruptedException {
        // 发送 3 条消息
        send(3);
    }


    @Test
    void send2() throws InterruptedException {
        send(11);
    }

    public void send(int num) throws InterruptedException {
        for (int i = 0; i < num; i++) {
            demo06ProviderlB.send("【send】 Hello RabbitMQ For Spring Boot! " + i);
            log.info("[send][发送编号：[{}] 发送成功]",i);
        }
        new CountDownLatch(1).await();
    }




}
