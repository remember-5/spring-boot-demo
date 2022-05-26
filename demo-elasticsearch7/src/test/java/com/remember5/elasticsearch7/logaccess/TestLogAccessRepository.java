package com.remember5.elasticsearch7.logaccess;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.github.javafaker.Faker;
import com.remember5.elasticsearch7.DemoElasticsearch7Application;
import com.remember5.elasticsearch7.entity.LogAccessEntity;
import com.remember5.elasticsearch7.repository.LogAccessEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author wangjiahao
 * @date 2022/5/27 00:08
 */
@SpringBootTest(classes= DemoElasticsearch7Application.class)
public class TestLogAccessRepository {

    @Autowired
    LogAccessEntityRepository logAccessEntityRepository;

    @Test
    public void testSelectLogAccess() {
        List<LogAccessEntity> all =
                logAccessEntityRepository.findAll();
        System.err.println(all.size());

    }

    @Test
    public void generatorData(){
        Faker faker = new Faker(new Locale("zh-CN"));
        Date now = new Date();
        int count = 100;
        for (int i = 0; i < count; i++) {

            Date between = faker.date().between(DateUtil.beginOfYear(now), now);
            LocalDateTime of = LocalDateTimeUtil.of(between);
            Timestamp timestamp = Timestamp.valueOf(of);
            LogAccessEntity logAccess = LogAccessEntity.builder()
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
                    .tCreate(timestamp)
                    .vBody(RandomUtil.randomString(20))
                    .idAtAppModule(RandomUtil.randomString(20))
                    .vDevice(RandomUtil.randomEle(CollUtil.newArrayList("PC", "Android", "iOS")))
                    .build();
            logAccessEntityRepository.save(logAccess);
        }
    }

}
