package com.remember.rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
//@EnableSecurity
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		String[] beanDefinitionNames = run.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).forEach(System.out::println);

	}

}
