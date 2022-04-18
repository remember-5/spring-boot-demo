package com.remember.dynamic.datasource.pgvsmysql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import com.remember.dynamic.datasource.entity.LogAccess;
import com.remember.dynamic.datasource.mapper.LogAccessMapper;
import com.remember.dynamic.datasource.service.LogAccessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author wangjiahao
 * @date 2022/4/16 19:07
 */
@Slf4j
@RunWith(SpringRunner.class)//设置启动器
@SpringBootTest(classes = {SpringBootDemoDynamicDatasourceApplication.class})
public class TestPostgresVsMysql {

    @Autowired
    LogAccessService logAccessService;

    @Test
   public void testInsert() {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Thread thread1 = new Thread(() -> {
                logAccessService.testBatchInsert();
        });
//        Thread thread2 = new Thread(() -> {
//            logAccessService.testBatchInsert();
//        });
//        Thread thread3 = new Thread(() -> {
//            logAccessService.testBatchInsert();
//        });
//        Thread thread4 = new Thread(() -> {
//            logAccessService.testBatchInsert();
//        });
//        Thread thread5 = new Thread(() -> {
//            logAccessService.testBatchInsert();
//        });

        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
//        thread5.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testFaker(){
        Faker faker = new Faker(new Locale("zh-CN"));
        final String url = faker.internet().url();
        System.err.println(url);
        System.err.println(faker.internet().ipV4Cidr());
        System.err.println(faker.internet().ipV4Address());
    }
}
