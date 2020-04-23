package com.remember.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>加密数据配置读取类</p>
 * <p>在SpringBoot项目中的application.yml中添加配置信息即可</p>
 * <pre>
 *     encrypt:
 *      body:
 *       aes-key: 12345678 # AES加密秘钥
 *       des-key: 12345678 # DES加密秘钥
 *       rsa-private-key: rsa的私钥
 *       rsa-publicKey: rsa的公钥
 *       rsa-open: 是否打开
 *       rsa-showLog: 是否打印日志
 * </pre>
 *
 * @author wangjiahao
 * @version 2018/9/6
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "encrypt.body")
public class EncryptBodyConfig {

    /**
     * aes的key
     */
    private String aesKey;

    /**
     * des的key
     */
    private String desKey;
}
