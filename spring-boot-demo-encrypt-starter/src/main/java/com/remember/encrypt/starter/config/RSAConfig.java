package com.remember.encrypt.starter.config;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2020/4/24
 */
@Data
public class RSAConfig {
    /**
     * rsa的privateKey
     */
    public String privateKey;
    /**
     * rsa的publicKey
     */
    public String publicKey;

    /**
     * 是否开启加密 true  or  false
     */
    public boolean open = true;

    /**
     * 是否打印加解密log true  or  false
     */
    public boolean showLog = false;

    /**
     * RSA最大加密明文大小
     * RSA Maximum Encrypted Plaintext Size
     */
    public int maxEncryptBlock;

    /**
     * RSA最大解密密文大小
     * RSA Maximum decrypted ciphertext size
     */
    public int maxDecryptBlock;
}
