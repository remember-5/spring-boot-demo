package com.remember.encrypt.starter.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64
 * @author wangjiahao
 * DateTime:2019/4/9
 *
 * */
public class Base64Util {

    /**
     * Decoding to binary
     * @param base64 base64
     * @return byte
     */
    public static byte[] decode(String base64){
        return Base64.decodeBase64(base64);
    }

    /**
     * Binary encoding as a string
     * @param bytes byte
     * @return String
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }
}
