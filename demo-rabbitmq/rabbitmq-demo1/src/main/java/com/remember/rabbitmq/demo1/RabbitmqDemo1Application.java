package com.remember.rabbitmq.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangjiahao
 */
@SpringBootApplication
@EnableAsync
public class RabbitmqDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemo1Application.class, args);
    }

}
