package com.remember.encrypt.starter.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * <p>DES加密处理工具类</p>
 *
 * @author wangjiahao
 * @version 2020/4/23
 */
public class DESEncryptUtil {

    /**
     * 生成一个DES密钥
     * @param length 传入56即可
     * @return DES密钥
     */
    public static String getKey(int length) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//            keyGenerator.init(56);
            // 生成一个Key
            keyGenerator.init(length);
            SecretKey generateKey = keyGenerator.generateKey();
            // 转变为字节数组
            byte[] encoded = generateKey.getEncoded();
            // 生成密钥字符串
            return Hex.encodeHexString(encoded);
        } catch (Exception e) {
            e.printStackTrace();
            return "密钥生成错误.";
        }
    }

    /**
     * DES加密
     *
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String encrypt(String content, String password) {
        return des(content, password, Cipher.ENCRYPT_MODE);
    }


    /**
     * DES解密
     *
     * @param content  字符串内容
     * @param password 密钥
     */
    public static String decrypt(String content, String password) {
        return des(content, password, Cipher.DECRYPT_MODE);
    }


    /**
     * DES加密/解密公共方法
     *
     * @param content  字符串内容
     * @param password 密钥
     * @param type     加密：{@link Cipher#ENCRYPT_MODE}，解密：{@link Cipher#DECRYPT_MODE}
     */
    private static String des(String content, String password, int type) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(type, keyFactory.generateSecret(desKey), random);

            if (type == Cipher.ENCRYPT_MODE) {
                byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
                return Hex2Util.parseByte2HexStr(cipher.doFinal(byteContent));
            } else {
                byte[] byteContent = Hex2Util.parseHexStr2Byte(content);
                assert byteContent != null;
                return new String(cipher.doFinal(byteContent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
