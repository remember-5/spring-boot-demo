package com.remember.redis.service;

import com.remember.redis.SpringBootDemoRedisApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.redis.util.RedisLockRegistry;

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
    @Resource
    RedisLockRegistry redisLockRegistry;

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

    /**
     * 测试springboot提供的锁
     * @throws InterruptedException /
     */
    @Test
    void testSpringBootLockRegister() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                log.info("线程 {} 启动了", Thread.currentThread().getName());
                Lock lock = redisLockRegistry.obtain(KEY);
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

}
