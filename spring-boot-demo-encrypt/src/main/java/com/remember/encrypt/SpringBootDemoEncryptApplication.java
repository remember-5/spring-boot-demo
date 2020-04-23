package com.remember.encrypt;

import com.remember.encrypt.annotation.EnableEncryptBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangjiahao
 * @date 2020/04/23
 */
@EnableEncryptBody
@SpringBootApplication
public class SpringBootDemoEncryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoEncryptApplication.class, args);
	}

}
