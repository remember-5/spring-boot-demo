package com.remember.encrypt.starter.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * <p>AES加密处理工具类</p>
 *
 * @author wangjiahao
 * @version 2020/4/23
 */
public class AESEncryptUtil {

    /**
     * 要生成多少位，只需要修改这里即可128, 192或256
     */
    public static final int AES_LENGTH = 128;

    /**
     * 随机生成秘钥
     * @param length 长度
     * @return 秘钥
     */
    public static String getKey(int length) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(length);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            String s = Hex2Util.parseByte2HexStr(b);
            System.out.println(s);
            System.out.println("十六进制密钥长度为" + s.length());
            System.out.println("二进制密钥的长度为" + s.length() * 4);
            return s;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
        return null;
    }


    /**
     * AES加密
     *
     * @param content  字符串内容
     * @param password 密钥
     * @return  AES加密
     */
    public static String encrypt(String content, String password) {
        return aes(content, password, Cipher.ENCRYPT_MODE);
    }


    /**
     * AES解密
     *
     * @param content  字符串内容
     * @param password 密钥
     * @return AES解密
     */
    public static String decrypt(String content, String password) {
        return aes(content, password, Cipher.DECRYPT_MODE);
    }

    /**
     * AES加密/解密 公共方法
     *
     * @param content  字符串
     * @param password 密钥
     * @param type     加密：{@link Cipher#ENCRYPT_MODE}，解密：{@link Cipher#DECRYPT_MODE}
     * @return  AES加密/解密
     */
    private static String aes(String content, String password, int type) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            generator.init(AES_LENGTH, random);
            SecretKey secretKey = generator.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(type, key);
            if (type == Cipher.ENCRYPT_MODE) {
                byte[] byteContent = content.getBytes("utf-8");
                return Hex2Util.parseByte2HexStr(cipher.doFinal(byteContent));
            } else {
                byte[] byteContent = Hex2Util.parseHexStr2Byte(content);
                return new String(cipher.doFinal(byteContent));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
