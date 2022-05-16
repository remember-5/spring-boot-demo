package com.remember.redis;

import cn.hutool.core.date.DateUtil;
import com.remember.redis.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@SpringBootTest()
class SpringBootDemoRedisApplicationTests {
    @Resource
    RedisUtils redisUtils;

    @Resource
    private RedissonClient redissonClient;

    @Test
    void testRedisUtilsSet() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            redisUtils.set("my"+i,i);
        }
        System.err.println(redissonClient.getBucket("my1").get());
    }

    @Test
    void testRedissonSet(){
        RBucket<String> nameBucket = redissonClient.getBucket("name");
        nameBucket.set("wangjiahao");
        System.err.println(nameBucket.getCodec());
        System.err.println(redissonClient.getBucket("name").get());
//        new CountDownLatch(1).await();
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
        redisUtils.set(key, 2);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                final RLock lock = redissonClient.getLock("lock");
                try {
                    lock.lock();
                    int a = Integer.parseInt(redisUtils.get(key).toString());
                    if (a > 0) {
                        --a;
                        System.err.println("抢到了，当前剩下" + a);
                        redisUtils.set(key, a);
                    } else {
                        System.err.println("没抢到");
                    }
                    Thread.sleep(3000L);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println(DateUtil.now());

            }
            ).start();
        }
        new CountDownLatch(1).await();
    }
}
