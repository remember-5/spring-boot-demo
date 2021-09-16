package com.remember.redis;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import com.remember.redis.entity.User;
import com.remember.redis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBootDemoRedisApplicationTests {


    @Autowired
    RedisService redisService;


    @Test
    void contextLoads() {
        for (int i = 0; i < 100; i++) {
            User user = new User("wangjiahao" + i, i, "上海浦东" + i);
            System.err.println(redisService.set("USER:ADMIN:" + i, user));
        }
    }

    @Test
    void contextLoads1() {
        List<Object> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User("wangjiahao" + i, i, "上海浦东" + i);
            users.add(user);
//            System.err.println(redisService.set("USER:ADMIN:" + i, user));
        }
        redisService.lSetAll("wangjiahao", users);
    }

    @Test
    void contextLoads2() {
//        User o = (User) redisService.get("USER:ADMIN:1");
//        System.err.println(o.getName());
//        System.err.println(o.getAddress());
//        System.err.println(o.getAge());

        List<Object> objects = redisService.lGet("wangjiahao", 0L, redisService.lGetListSize("wangjiahao"));
        List<User> collect = objects.stream().map(e -> (User) e).collect(Collectors.toList());
        collect.forEach(user -> {
            System.err.println(user.getAddress());
            System.err.println(user.getAge());
            System.err.println(user.getName());
        });

    }


    @Test
    void getKeys() {
        for (String key : redisService.getKeys("USER:ADMIN:*")) {
//            System.err.println(key);
            System.err.println(redisService.get(key));
        }
    }


}
