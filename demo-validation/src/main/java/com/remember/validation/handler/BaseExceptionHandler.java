package com.remember.validation.handler;

import com.remember.common.entity.R;
import com.remember.common.entity.ResultEnum;
import com.remember.validation.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @author Zone
 * @version 1.0
 * @date 2020/4/9 12:28
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public String notDecryptException(BaseException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.debug("[constraintViolationExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中，这个不包含报错字段
            // detailMessage.append(constraintViolation.getPropertyPath());
            // detailMessage.append(":");
            detailMessage.append(constraintViolation.getMessage());
        }
        // 包装 CommonResult 结果
        return new R().code(ResultEnum.A0400.code).message(detailMessage.toString());
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public R bindExceptionHandler(BindException ex) {
        log.debug("[bindExceptionHandler]", ex);
        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ObjectError objectError : ex.getAllErrors()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(";");
            }
            // 拼接内容到其中
            detailMessage.append(objectError.getDefaultMessage());
        }
        // 包装 CommonResult 结果
        return new R().code(ResultEnum.A0400.code).message(detailMessage.toString());
    }

}
