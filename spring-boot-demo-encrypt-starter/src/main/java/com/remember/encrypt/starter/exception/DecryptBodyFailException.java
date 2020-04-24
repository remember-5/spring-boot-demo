package com.remember.encrypt.starter.exception;

/**
 * <p>解密数据失败异常</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
public class DecryptBodyFailException extends RuntimeException {

    private static final long serialVersionUID = -4006760482981813625L;

    public DecryptBodyFailException() {
        super("Decrypting data failed. (解密数据失败)");
    }

    public DecryptBodyFailException(String message) {
        super(message);
    }
}