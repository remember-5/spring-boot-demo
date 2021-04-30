package com.remember.spi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/4/22
 */
@Component
@Order(0)
public class AYellowPersion implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.err.println("----AYellowPersion----");
    }
}
