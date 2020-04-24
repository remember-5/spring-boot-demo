package com.remember.encrypt.starter.exception;

/**
 * <p>加密数据失败异常</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
public class EncryptBodyFailException  extends RuntimeException {

    private static final long serialVersionUID = 4107092879997448594L;

    public EncryptBodyFailException() {
        super("Encrypted data failed. (加密数据失败)");
    }

    public EncryptBodyFailException(String message) {
        super(message);
    }
}