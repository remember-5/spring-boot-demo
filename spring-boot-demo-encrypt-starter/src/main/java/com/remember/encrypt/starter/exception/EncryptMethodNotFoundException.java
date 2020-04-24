package com.remember.encrypt.starter.exception;

/**
 * <p>加密方式未找到或未定义异常</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
public class EncryptMethodNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6605497257705955415L;

    public EncryptMethodNotFoundException() {
        super("Encryption method is not defined. (加密方式未定义)");
    }

    public EncryptMethodNotFoundException(String message) {
        super(message);
    }
}
