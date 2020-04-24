package com.remember.encrypt.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2020/4/24
 */
@Configuration
@EnableConfigurationProperties(EncryptConfig.class)
@ConditionalOnProperty(value = "encrypt.enable", havingValue = "true", matchIfMissing = true)
public class EncryptAutoConfigure {

}
