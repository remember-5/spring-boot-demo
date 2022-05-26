package com.remember.dynamic.datasource.mybatisplus.service;

import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import com.remember.dynamic.datasource.mybatisplus.service.impl.MybatisPlusLogAccessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringBootDemoDynamicDatasourceApplication.class)
public class LogAccessServiceTest {

    @Autowired
    MybatisPlusLogAccessServiceImpl logAccessService;

    @Test
    public void mysqlInsert() {
        logAccessService.mysqlInsert();
    }

    @Test
    public void postgresInsert() {
        logAccessService.postgresInsert();
    }
}