package com.remember.encrypt.starter.util;


import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA Util
 *
 * @author wangjiahao
 * @version  2019/4/9
 **/
public class RSAUtil {

    /**
     * encryption algorithm RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * encryption
     *
     * @param data      data
     * @param publicKey publicKey
     * @param maxEncryptBlock maxEncryptBlock
     * @return byte
     */
    public static byte[] encrypt(byte[] data, String publicKey, int maxEncryptBlock) {
        try {
            byte[] keyBytes = Base64Util.decode(publicKey);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicK = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicK);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // Sectional Encryption of Data
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxEncryptBlock) {
                    cache = cipher.doFinal(data, offSet, maxEncryptBlock);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxEncryptBlock;
            }
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Decrypt
     *
     * @param text       text
     * @param privateKey privateKey
     * @param maxDecryptBlock maxDecryptBlock
     * @return byte
     */
    public static byte[] decrypt(byte[] text, String privateKey, int maxDecryptBlock) {
        try {
            byte[] keyBytes = Base64Util.decode(privateKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateK);
            int inputLen = text.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;
            // Sectional Encryption of Data
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > maxDecryptBlock) {
                    cache = cipher.doFinal(text, offSet, maxDecryptBlock);
                } else {
                    cache = cipher.doFinal(text, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * maxDecryptBlock;
            }
            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
