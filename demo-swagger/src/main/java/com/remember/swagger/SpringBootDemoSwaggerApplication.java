package com.remember.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoSwaggerApplication {

    public static void main(String[] args) {
        for (String arg : args) {
            System.err.println(arg);
        }
        System.err.println(System.getProperty("name"));
        SpringApplication.run(SpringBootDemoSwaggerApplication.class, args);
    }

}
