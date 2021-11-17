package com.remember5.rabbitmq.provider;

import com.remember5.rabbitmq.message.Demo4Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Slf4j
@SpringBootTest
public class Send4Test {

    @Resource
    private Demo4Provider demo4Privider;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Test
    void test1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            demo4Privider.send("【send】 Hello RabbitMQ For Spring Boot! " + i, Demo4Message.HEADER_VALUE);
        }
        new CountDownLatch(1).await();
    }


    @Test
    void test2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            demo4Privider.send("【send】 Hello RabbitMQ For Spring Boot! " + i, "error");
        }
        new CountDownLatch(1).await();
    }
}
