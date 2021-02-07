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

        for (int i = 0; i < 100; i++) {
            User user = new User("wangjiahao"+i, i, "上海浦东"+i);
            System.err.println(redisService.set("USER:ADMIN:"+i, user));
        }
    }

    @Test
    void getKeys(){
        for (String key : redisService.getKeys("USER:ADMIN:*")) {
//            System.err.println(key);
            System.err.println(redisService.get(key));
        }
    }


}
