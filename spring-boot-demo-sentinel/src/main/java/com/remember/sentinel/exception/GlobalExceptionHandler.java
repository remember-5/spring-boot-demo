package com.remember.sentinel.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangjiahao
 * @date 2020/4/7
 */
@ControllerAdvice("com.remember.sentinel.controller")
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BlockException.class)
    public String blockExceptionHandler(BlockException blockException){
        return "请求过于频繁";
    }


}

