package com.remember.encrypt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * @author wangjiahao
 * @date 2020/04/23
 */
@SpringBootApplication
public class SpringBootDemoEncryptApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoEncryptApplication.class, args);
		String property = run.getEnvironment().getProperty("encrypt.enable");
		System.err.println(property);

		String[] beanDefinitionNames = run.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).forEach(System.err::println);

	}

}
