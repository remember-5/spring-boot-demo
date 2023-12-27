package com.remember5.interview.encrypt;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.springframework.util.Base64Utils;

/**
 * aes加密
 *
 * @author wangjiahao
 * @date 2020/6/11
 */
public class AesDemo {

    public static void main(String[] args) {
        String content = "test中文";

        while (true) {
            //随机生成密钥
            byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
            String s = Base64Utils.encodeToString(key);
            System.err.println(s);
        }
//        String keyStr = "ujZVrb2CcKR3cdAhhnft4g==";
//        byte[] key = keyStr.getBytes();
//
//        //构建
//        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
//
//        //加密
//        byte[] encrypt = aes.encrypt(content);
//        //解密
//        byte[] decrypt = aes.decrypt(encrypt);
//
//        //加密为16进制表示
//        String encryptHex = aes.encryptHex(content);
//        System.err.println(encryptHex);
//        //解密为字符串
//        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
//        System.err.println(decryptStr);
    }

}
