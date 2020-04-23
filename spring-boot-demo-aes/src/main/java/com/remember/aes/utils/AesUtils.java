package com.remember.aes.utils;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @author wangjiahao
 * @date 2020/3/10
 */
//@SuppressWarnings("all")
public class AesUtils {

    /**
     * 加密为16进制表示
     * 随机生成密钥
     * SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
     *
     * @param key     key
     * @param content content
     * @return 加密结果
     */
    public static String encodeHex(String key, String content) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return aes.encryptHex(content);
    }
    
    /**
     * 加密为16进制表示
     *
     * @param key       key
     * @param encodeHex encodeHex
     * @return 加密结果
     */
    public static String decodeHex(String key, String encodeHex) {
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return aes.decryptStr(encodeHex, CharsetUtil.CHARSET_UTF_8);
    }
}
