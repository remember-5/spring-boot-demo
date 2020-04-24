package com.remember.encrypt.starter.exception;


import lombok.NoArgsConstructor;

/**
 * <p>未配置KEY运行时异常</p>
 * @author wangjiahao
 * @version 2018/9/6
 */
@NoArgsConstructor
public class KeyNotConfiguredException extends RuntimeException {

    private static final long serialVersionUID = 3015643057890492542L;

    public KeyNotConfiguredException(String message) {
        super(message);
    }
}
