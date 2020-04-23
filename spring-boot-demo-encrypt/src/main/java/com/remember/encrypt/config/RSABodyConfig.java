package com.remember.encrypt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2020/4/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "encrypt.body.rsa")
public class RSABodyConfig {

    /**
     * rsa的privateKey
     */
    private String privateKey;
    /**
     * rsa的publicKey
     */
    private String publicKey;

    /**
     * 是否开启加密 true  or  false
     */
    private boolean open = true;

    /**
     * 是否打印加解密log true  or  false
     */
    private boolean showLog = false;

    /**
     * RSA最大加密明文大小
     * RSA Maximum Encrypted Plaintext Size
     */
    private int maxEncryptBlock;

    /**
     * RSA最大解密密文大小
     * RSA Maximum decrypted ciphertext size
     */
    private int maxDecryptBlock;
}
