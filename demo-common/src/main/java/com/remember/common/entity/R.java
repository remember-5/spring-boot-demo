package com.remember.common.entity;

import java.io.Serializable;

/**
 * 统一返回
 * @author wangjiahao
 * @date 2021/12/30
 */
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回信息
     */
    private String message;

    public R(T data) {
        this(ResultEnum.A00000, data);
    }

    public R(ResultEnum resultEnum) {
        this(resultEnum.code, null, resultEnum.message);
    }

    public R(ResultEnum resultEnum, T data) {
        this(resultEnum.code, data, resultEnum.message);
    }

    public R(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> R<T> success() {
        return success(ResultEnum.A00000, null);
    }

    public static <T> R<T> success(T data) {
        return success(ResultEnum.A00000, data);
    }

    public static <T> R<T> success(ResultEnum resultEnum, T data) {
        return success(resultEnum.code, data, resultEnum.message);
    }

    public static <T> R<T> success(String code, T data, String message) {
        return new R<T>(code, data, message);
    }

    public static <T> R<T> fail(ResultEnum resultEnum) {
        return fail(resultEnum, null);
    }

    public static <T> R<T> fail(ResultEnum resultEnum, T data) {
        return fail(resultEnum.code, data, resultEnum.message);
    }

    public static <T> R<T> fail(String code, T data, String message) {
        return new R<T>(code, data, message);
    }
}
