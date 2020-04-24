package com.remember.encrypt.starter.exception;

/**
 * <p>加密方式未找到或未定义异常</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
public class DecryptMethodNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7273567553289801145L;

    public DecryptMethodNotFoundException() {
        super("Decryption method is not defined. (解密方式未定义)");
    }

    public DecryptMethodNotFoundException(String message) {
        super(message);
    }
}
