package com.remember.spi;

import com.remember.spi.service.HelloInterface;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ServiceLoader;

@SpringBootTest
class SpringBootDemoSpiApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void HelloSpiTest() {
        ServiceLoader<HelloInterface> serviceLoader = ServiceLoader.load(HelloInterface.class);

        for (HelloInterface hello: serviceLoader) {
            hello.sayHello();
        }
    }
}
