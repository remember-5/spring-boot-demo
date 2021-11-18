package com.remember.rabbitmq.demo6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo6Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo6Application.class, args);
    }

}
