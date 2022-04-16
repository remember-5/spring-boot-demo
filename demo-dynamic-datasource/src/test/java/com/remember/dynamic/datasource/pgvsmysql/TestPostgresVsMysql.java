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
    LogAccessMapper logAccessMapper;
    @Autowired
    LogAccessService logAccessService;

    @Test
   public void testInsert() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Faker faker = new Faker(new Locale("zh-CN"));
        Date now = new Date();

        Thread thread1 = new Thread(() -> {
            int count = 1;
            while (count < 10000) {
                ArrayList<LogAccess> logAccesses = new ArrayList<>();
                TimeInterval timer = DateUtil.timer();
                for (int i = 0; i < 1000; i++) {
                    LogAccess logAccess = LogAccess.builder()
                            .vMethod(RandomUtil.randomEle(CollUtil.newArrayList("GET", "POST", "PUT", "DELETE")))
                            .vUri(faker.internet().url())
                            .vIp(faker.internet().ipV4Address())
                            .iStatus(RandomUtil.randomEle(CollUtil.newArrayList(200, 302, 400, 401, 403, 404, 413, 500)))
                            .vType(RandomUtil.randomEle(CollUtil.newArrayList("01", "02", "03", "04", "05", "06", "07")))
                            .vBrowser(RandomUtil.randomEle(CollUtil.newArrayList("Chrome 8", "Chrome 9", "Chrome 10", "IE", "FireFox")))
                            .bSuccess(RandomUtil.randomBoolean())
                            .vApplication(RandomUtil.randomString(10))
                            .vDataId(RandomUtil.randomString(20))
                            .vAliasAtAppModule(RandomUtil.randomString(20))
                            .vAliasAtAppModuleFunction(RandomUtil.randomString(20))
                            .bSkip(RandomUtil.randomBoolean())
                            .idAtAuthUser(UUID.randomUUID().toString(true))
                            .tCreate(faker.date().between(DateUtil.beginOfYear(now), now)) // 今年的第一天
                            .vBody(RandomUtil.randomString(20))
                            .idAtAppModule(RandomUtil.randomString(20))
                            .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
                            .build();
                    logAccesses.add(logAccess);
                }
                log.info("{},生成代码耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
                logAccessService.saveBatch(logAccesses);
                log.info("{},写入sql耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
                count += 1;
                log.info("{}, {}",Thread.currentThread().getName(),count);

            }
        });
//        Thread thread2 = new Thread(() -> {
//            int count = 1;
//            while (count < 10000) {
//                ArrayList<LogAccess> logAccesses = new ArrayList<>();
//                TimeInterval timer = DateUtil.timer();
//                for (int i = 0; i < 10000; i++) {
//                    LogAccess logAccess = LogAccess.builder()
//                            .vMethod(RandomUtil.randomEle(CollUtil.newArrayList("GET", "POST", "PUT", "DELETE")))
//                            .vUri(faker.internet().url())
//                            .vIp(faker.internet().ipV4Address())
//                            .iStatus(RandomUtil.randomEle(CollUtil.newArrayList(200, 302, 400, 401, 403, 404, 413, 500)))
//                            .vType(RandomUtil.randomEle(CollUtil.newArrayList("01", "02", "03", "04", "05", "06", "07")))
//                            .vBrowser(RandomUtil.randomEle(CollUtil.newArrayList("Chrome 8", "Chrome 9", "Chrome 10", "IE", "FireFox")))
//                            .bSuccess(RandomUtil.randomBoolean())
//                            .vApplication(RandomUtil.randomString(10))
//                            .vDataId(RandomUtil.randomString(20))
//                            .vAliasAtAppModule(RandomUtil.randomString(20))
//                            .vAliasAtAppModuleFunction(RandomUtil.randomString(20))
//                            .bSkip(RandomUtil.randomBoolean())
//                            .idAtAuthUser(UUID.randomUUID().toString(true))
//                            .tCreate(faker.date().between(DateUtil.beginOfYear(now), now)) // 今年的第一天
//                            .vBody(RandomUtil.randomString(20))
//                            .idAtAppModule(RandomUtil.randomString(20))
//                            .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
//                            .build();
//                    logAccesses.add(logAccess);
//                }
//                log.info("{},生成代码耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                logAccessService.saveBatch(logAccesses);
//                log.info("{},写入sql耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                count += 1;
//                log.info("{}, {}",Thread.currentThread().getName(),count);
//
//            }
//        });
//        Thread thread3 = new Thread(() -> {
//            int count = 1;
//            while (count < 10000) {
//                ArrayList<LogAccess> logAccesses = new ArrayList<>();
//                TimeInterval timer = DateUtil.timer();
//                for (int i = 0; i < 10000; i++) {
//                    LogAccess logAccess = LogAccess.builder()
//                            .vMethod(RandomUtil.randomEle(CollUtil.newArrayList("GET", "POST", "PUT", "DELETE")))
//                            .vUri(faker.internet().url())
//                            .vIp(faker.internet().ipV4Address())
//                            .iStatus(RandomUtil.randomEle(CollUtil.newArrayList(200, 302, 400, 401, 403, 404, 413, 500)))
//                            .vType(RandomUtil.randomEle(CollUtil.newArrayList("01", "02", "03", "04", "05", "06", "07")))
//                            .vBrowser(RandomUtil.randomEle(CollUtil.newArrayList("Chrome 8", "Chrome 9", "Chrome 10", "IE", "FireFox")))
//                            .bSuccess(RandomUtil.randomBoolean())
//                            .vApplication(RandomUtil.randomString(10))
//                            .vDataId(RandomUtil.randomString(20))
//                            .vAliasAtAppModule(RandomUtil.randomString(20))
//                            .vAliasAtAppModuleFunction(RandomUtil.randomString(20))
//                            .bSkip(RandomUtil.randomBoolean())
//                            .idAtAuthUser(UUID.randomUUID().toString(true))
//                            .tCreate(faker.date().between(DateUtil.beginOfYear(now), now)) // 今年的第一天
//                            .vBody(RandomUtil.randomString(20))
//                            .idAtAppModule(RandomUtil.randomString(20))
//                            .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
//                            .build();
//                    logAccesses.add(logAccess);
//                }
//                log.info("{},生成代码耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                logAccessService.saveBatch(logAccesses);
//                log.info("{},写入sql耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                count += 1;
//                log.info("{}, {}",Thread.currentThread().getName(),count);
//
//            }
//        });
//        Thread thread4 = new Thread(() -> {
//            int count = 1;
//            while (count < 10000) {
//                ArrayList<LogAccess> logAccesses = new ArrayList<>();
//                TimeInterval timer = DateUtil.timer();
//                for (int i = 0; i < 10000; i++) {
//                    LogAccess logAccess = LogAccess.builder()
//                            .vMethod(RandomUtil.randomEle(CollUtil.newArrayList("GET", "POST", "PUT", "DELETE")))
//                            .vUri(faker.internet().url())
//                            .vIp(faker.internet().ipV4Address())
//                            .iStatus(RandomUtil.randomEle(CollUtil.newArrayList(200, 302, 400, 401, 403, 404, 413, 500)))
//                            .vType(RandomUtil.randomEle(CollUtil.newArrayList("01", "02", "03", "04", "05", "06", "07")))
//                            .vBrowser(RandomUtil.randomEle(CollUtil.newArrayList("Chrome 8", "Chrome 9", "Chrome 10", "IE", "FireFox")))
//                            .bSuccess(RandomUtil.randomBoolean())
//                            .vApplication(RandomUtil.randomString(10))
//                            .vDataId(RandomUtil.randomString(20))
//                            .vAliasAtAppModule(RandomUtil.randomString(20))
//                            .vAliasAtAppModuleFunction(RandomUtil.randomString(20))
//                            .bSkip(RandomUtil.randomBoolean())
//                            .idAtAuthUser(UUID.randomUUID().toString(true))
//                            .tCreate(faker.date().between(DateUtil.beginOfYear(now), now)) // 今年的第一天
//                            .vBody(RandomUtil.randomString(20))
//                            .idAtAppModule(RandomUtil.randomString(20))
//                            .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
//                            .build();
//                    logAccesses.add(logAccess);
//                }
//                log.info("{},生成代码耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                logAccessService.saveBatch(logAccesses);
//                log.info("{},写入sql耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                count += 1;
//                log.info("{}, {}",Thread.currentThread().getName(),count);
//
//            }
//        });
//        Thread thread5 = new Thread(() -> {
//            int count = 1;
//            while (count < 10000) {
//                ArrayList<LogAccess> logAccesses = new ArrayList<>();
//                TimeInterval timer = DateUtil.timer();
//                for (int i = 0; i < 10000; i++) {
//                    LogAccess logAccess = LogAccess.builder()
//                            .vMethod(RandomUtil.randomEle(CollUtil.newArrayList("GET", "POST", "PUT", "DELETE")))
//                            .vUri(faker.internet().url())
//                            .vIp(faker.internet().ipV4Address())
//                            .iStatus(RandomUtil.randomEle(CollUtil.newArrayList(200, 302, 400, 401, 403, 404, 413, 500)))
//                            .vType(RandomUtil.randomEle(CollUtil.newArrayList("01", "02", "03", "04", "05", "06", "07")))
//                            .vBrowser(RandomUtil.randomEle(CollUtil.newArrayList("Chrome 8", "Chrome 9", "Chrome 10", "IE", "FireFox")))
//                            .bSuccess(RandomUtil.randomBoolean())
//                            .vApplication(RandomUtil.randomString(10))
//                            .vDataId(RandomUtil.randomString(20))
//                            .vAliasAtAppModule(RandomUtil.randomString(20))
//                            .vAliasAtAppModuleFunction(RandomUtil.randomString(20))
//                            .bSkip(RandomUtil.randomBoolean())
//                            .idAtAuthUser(UUID.randomUUID().toString(true))
//                            .tCreate(faker.date().between(DateUtil.beginOfYear(now), now)) // 今年的第一天
//                            .vBody(RandomUtil.randomString(20))
//                            .idAtAppModule(RandomUtil.randomString(20))
//                            .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
//                            .build();
//                    logAccesses.add(logAccess);
//                }
//                log.info("{},生成代码耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                logAccessService.saveBatch(logAccesses);
//                log.info("{},写入sql耗时 {}",Thread.currentThread().getName(),timer.intervalMs());
//                count += 1;
//                log.info("{}, {}",Thread.currentThread().getName(),count);
//
//            }
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
