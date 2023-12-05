package com.remember.dynamic.datasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 多数据源
 *
 * @author wangjiahao
 */
@SpringBootApplication
@EnableTransactionManagement
public class SpringBootDemoDynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoDynamicDatasourceApplication.class, args);
    }

}
