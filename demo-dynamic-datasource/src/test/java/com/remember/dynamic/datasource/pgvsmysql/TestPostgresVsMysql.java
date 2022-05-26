package com.remember.dynamic.datasource.pgvsmysql;

import com.github.javafaker.Faker;
import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import com.remember.dynamic.datasource.mybatisplus.service.MybatisPlusLogAccessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;


/**
 * @author wangjiahao
 * @date 2022/4/16 19:07
 */
@Slf4j
@SpringBootTest(classes = {SpringBootDemoDynamicDatasourceApplication.class})
public class TestPostgresVsMysql {

    @Autowired
    MybatisPlusLogAccessService logAccessService;

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
