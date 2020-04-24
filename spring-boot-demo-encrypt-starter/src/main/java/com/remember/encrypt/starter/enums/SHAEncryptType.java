package com.remember.encrypt.starter.enums;

import lombok.AllArgsConstructor;

/**
 * <p>SHA加密类型</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
@AllArgsConstructor
public enum  SHAEncryptType {
    /**
     * SHA加密类型
     */
    SHA224("sha-224"),
    SHA256("sha-256"),
    SHA384("sha-384"),
    SHA512("sha-512");


    public String value;


}
