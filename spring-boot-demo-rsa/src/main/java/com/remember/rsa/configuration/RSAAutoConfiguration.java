package com.remember.rsa.configuration;

import com.remember.rsa.annotation.EnableSecurity;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2020/4/22
 */
@Configuration
@EnableSecurity
//@ConditionalOnProperty(name = "rasAutoConfiguration", havingValue = "true")
public class RSAAutoConfiguration {
}
