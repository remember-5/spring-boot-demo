package com.remember.dynamic.datasource.jpa.service;

import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplication;
import com.remember.dynamic.datasource.jpa.service.impl.JpaLogAccessServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootDemoDynamicDatasourceApplication.class)
public class JpaLogAccessServiceTest {

    @Autowired
    JpaLogAccessServiceImpl jpaLogAccessService;

    @Test
    public void mysqlInsert() {
        jpaLogAccessService.mysqlInsert();
    }

    @Test
    public void postgresInsert() {
        jpaLogAccessService.postgresInsert();
    }
}