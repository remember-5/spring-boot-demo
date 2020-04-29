package com.remember.redis;

import com.remember.redis.entity.User;
import com.remember.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootDemoRedisApplicationTests {


    @Autowired
    RedisService redisService;


    @Test
    void contextLoads() {
        User user = new User("wangjiahao", 24, "上海浦东");
        System.err.println(redisService.set("wangjiahao", user));

    }

}
