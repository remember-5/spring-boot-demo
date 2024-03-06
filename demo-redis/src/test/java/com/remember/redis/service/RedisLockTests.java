package com.remember.redis.service;

import com.remember.redis.SpringBootDemoRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @author wangjiahao
 * @date 2022/5/19 19:02
 */
@Slf4j
@SpringBootTest(classes = SpringBootDemoRedisApplication.class)
public class RedisLockTests {

    @Resource
    RedissonClient redissonClient;

    public static final String KEY = "mylock";
    /**
     * 测试redisson的锁
     * @throws InterruptedException /
     */
    @Test
    void testRedissonLock() throws InterruptedException {

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                log.info("线程 {} 启动了", Thread.currentThread().getName());
                Lock lock = redissonClient.getLock(KEY);
                lock.lock();
                try {
                    log.info("线程 {} 抢到了锁", Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                    log.info("线程 {} 释放了锁", Thread.currentThread().getName());
                }
            }).start();
        }

        new CountDownLatch(1).await();
    }

    @Test
    void testLock1() {
        // 创建分布式锁对象
        RLock lock = redissonClient.getLock("testLock1");
        // 尝试加锁，最多等待10秒，锁定5秒后自动释放
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock(10, 5, java.util.concurrent.TimeUnit.SECONDS);
            if (isLocked) {
                // 成功获得锁，执行业务逻辑
                System.out.println("Lock acquired. Performing business logic.");
                // ... 业务逻辑 ...
            } else {
                // 无法获得锁，执行相应的处理
                System.out.println("Unable to acquire lock. Performing alternative logic.");
                // ... 备选逻辑 ...
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            if (isLocked) {
                lock.unlock();
                System.out.println("Lock released.");
            }

            // 关闭 Redisson 客户端
            redissonClient.shutdown();
        }
    }

}
