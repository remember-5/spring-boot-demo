package com.remember5.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync // 开启异步
public class SpringBootDemoRabbitMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoRabbitMQApplication.class, args);
    }
}
