package com.example.rabbitmq.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo3Application.class, args);
    }

}
