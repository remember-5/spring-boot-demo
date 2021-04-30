package com.remember.jwt.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
