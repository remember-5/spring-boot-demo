package com.remember.dubbo.provide;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class NacosDubboProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDubboProvideApplication.class, args);
    }

}
