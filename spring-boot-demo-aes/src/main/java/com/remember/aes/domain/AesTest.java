package com.remember.aes.domain;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.remember.aes.utils.AesUtils;

/**
 * @author wangjiahao
 * @date 2020/4/23
 */
public class AesTest {

    public static void main(String[] args) {
        String content = "test中文";
//        test1(content);
        String key = "0CoJUm6Qyw8W8jud";
        String encodeHex = AesUtils.encodeHex(key, content);
        System.err.println(encodeHex);
        content = "中文test";
        String encodeHex1 = AesUtils.encodeHex(key, content);
        System.err.println(encodeHex1);

        System.err.println(AesUtils.decodeHex(key, encodeHex));
        System.err.println(AesUtils.decodeHex(key, encodeHex1));


    }


    private static void test1(String content){

        // 随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        System.err.println(new String(key));

        // 构建
        AES aes = SecureUtil.aes(key);

        // 加密
        byte[] encrypt = aes.encrypt(content);
        // 解密
        byte[] decrypt = aes.decrypt(encrypt);

        // 加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.err.println(encryptHex);
        // 解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.err.println(decryptStr);
    }

}
