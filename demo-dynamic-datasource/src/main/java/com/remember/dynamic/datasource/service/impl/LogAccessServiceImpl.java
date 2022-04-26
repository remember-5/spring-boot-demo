package com.remember.dynamic.datasource.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.util.RandomUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.dynamic.datasource.mapper.LogAccessMapper;
import com.remember.dynamic.datasource.entity.LogAccess;
import com.remember.dynamic.datasource.service.LogAccessService;
@Slf4j
@Service
public class LogAccessServiceImpl extends ServiceImpl<LogAccessMapper, LogAccess> implements LogAccessService{

    Faker faker = new Faker(new Locale("zh-CN"));

    @Override
    public void testBatchInsert() {
        ExecutorService executor = ExecutorBuilder.create()
                .setCorePoolSize(5)
                .setMaxPoolSize(10)
                .useSynchronousQueue()
                .build();
        for (int i = 0; i < 5; i++) {
            executor.execute(this::doInsert);
        }
    }

    private void doInsert(){
        Date now = new Date();
        int count = 1;
        while (count < 2000) {
            ArrayList<LogAccess> logAccesses = new ArrayList<>();
            TimeInterval timer = DateUtil.timer();
            for (int i = 0; i < 10000; i++) {
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
                        // 今年的第一天
                        .tCreate(faker.date().between(DateUtil.beginOfYear(now), now))
                        .vBody(RandomUtil.randomString(20))
                        .idAtAppModule(RandomUtil.randomString(20))
                        .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
                        .build();
                logAccesses.add(logAccess);
            }
            log.info("{},生成代码耗时 {}", Thread.currentThread().getName(), timer.intervalMs());
            super.saveBatch(logAccesses);
            log.info("{},写入sql耗时 {}", Thread.currentThread().getName(), timer.intervalMs());
            count+=1;
            log.info("{}, {}", Thread.currentThread().getName(), count);
        }

    }
}
