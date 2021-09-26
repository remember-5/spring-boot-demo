package com.remember.redis;

import cn.hutool.core.date.DateUtil;
import com.remember.redis.entity.User;
import com.remember.redis.service.RedisLockService;
import com.remember.redis.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class SpringBootDemoRedisApplicationTests {
    @Resource
    RedisUtils redisUtils;

    @Resource
    RedisLockService redisLockService;


    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            User user = new User("wangjiahao" + i, i, "上海浦东" + i);
            System.err.println(redisUtils.set("USER:ADMIN:" + i, user));
        }
    }

    @Test
    void getKeys() {
        for (Object key : redisUtils.getKeys("USER:ADMIN:*")) {
//            System.err.println(key);
            System.err.println(redisUtils.get(key.toString()));
        }
    }


    @Test
    public void lockTest() throws InterruptedException {
        String key = "ORDER1";
        redisUtils.set(key,1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                redisLockService.lock(key);
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(DateUtil.now());
                redisLockService.unlock(key);
            }
            ).start();
        }
        new CountDownLatch(1).await();
    }
}
