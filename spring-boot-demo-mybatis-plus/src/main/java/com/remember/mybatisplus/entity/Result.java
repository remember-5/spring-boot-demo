package com.remember.mybatisplus.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2020/4/30
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private Object data;

}
