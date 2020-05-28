package com.remember.jwt.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:28
 */
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public String notDecryptException(BaseException e){
        return e.getMessage();
    }


}
