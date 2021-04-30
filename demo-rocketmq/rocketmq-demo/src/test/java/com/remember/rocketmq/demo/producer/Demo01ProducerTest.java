package com.remember.rocketmq.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangjihao
 * @date 2020/12/2
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo01ProducerTest {


    @Autowired
    private Demo01Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult result = producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        // 阻塞等待保证消费
        new CountDownLatch(1).await();
    }


    @Test
    public void testASyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(id, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, throwable);
            }
        });
        new CountDownLatch(1).await();
    }


    @Test
    public void testOnewaySend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.onewaySend(id);
        log.info("[testOnewaySend][发送编号：[{}] 发送完成]", id);

        new CountDownLatch(1).await();

    }
}
