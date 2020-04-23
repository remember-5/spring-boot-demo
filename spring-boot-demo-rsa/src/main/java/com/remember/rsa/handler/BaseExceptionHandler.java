package com.remember.rsa.handler;

import com.remember.rsa.exception.BaseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:28
 */
@ControllerAdvice
public class BaseExceptionHandler {

    private static final Log logger = LogFactory.getLog(BaseExceptionHandler.class);

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public String notDecryptException(BaseException e){
        logger.error("请求未加密" + e.getMessage());
        return e.getMessage();
    }


}
