package com.remember.spi;

import com.remember.spi.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootDemoSpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoSpiApplication.class, args);

        // 返回 IOC 容器，使用注解配置，传入配置类 获取user
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
//        User user = context.getBean("user", User.class);
//        // 根据baneType获取beanName
//        String[] beanNamesForType = context.getBeanNamesForType(User.class);
//        for (int i = 0; i < beanNamesForType.length; i++) {
//            System.err.println(beanNamesForType[i]);
//        }
//        System.err.println(user);

        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        // 查看基于注解的 IOC容器中所有组件名称
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(System.out::println);


    }

}
