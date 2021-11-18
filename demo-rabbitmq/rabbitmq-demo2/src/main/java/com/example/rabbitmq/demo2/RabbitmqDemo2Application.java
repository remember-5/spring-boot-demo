package com.example.rabbitmq.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo2Application.class, args);
    }

}
