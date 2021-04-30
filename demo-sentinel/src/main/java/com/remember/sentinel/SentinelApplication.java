package com.remember.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangjiahao
 * @date 2020/4/7
 */
@SpringBootApplication
public class SentinelApplication {

    public static void main(String[] args) {
        // <X> 设置系统属性 project.name，提供给 Sentinel 读取
        System.setProperty("project.name", "demo-application");

        // 启动 Spring Boot 应用
        SpringApplication.run(SentinelApplication.class, args);
    }

}
