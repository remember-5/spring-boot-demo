package com.example.rabbitmq.demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo4Application.class, args);
    }

}
