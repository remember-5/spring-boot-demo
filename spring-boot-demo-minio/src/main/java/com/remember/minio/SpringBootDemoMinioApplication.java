package com.remember.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
public class SpringBootDemoMinioApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoMinioApplication.class, args);
//		ConfigurableApplicationContext run = SpringApplication.run(SpringBootDemoMinioApplication.class, args);
//		Arrays.asList(run.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
