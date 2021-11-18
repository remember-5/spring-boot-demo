package com.example.rabbitmq.demo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo5Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo5Application.class, args);
    }

}
