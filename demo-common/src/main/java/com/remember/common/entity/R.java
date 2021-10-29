package com.remember.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangjiahao
 * @date 2021/10/29
 */
@Data
//开启setter方法的链式调用
@Accessors(chain = true)
public class R {

    private int code;

    private String message;

    private Object data;


}
