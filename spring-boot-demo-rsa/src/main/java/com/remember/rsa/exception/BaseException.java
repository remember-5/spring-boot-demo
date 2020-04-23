package com.remember.rsa.exception;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:24
 */
public class BaseException extends RuntimeException{
    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
