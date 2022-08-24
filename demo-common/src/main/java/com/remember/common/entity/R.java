package com.remember.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 统一返回
 *
 * @author wangjiahao
 * @date 2021/12/30
 */
@Getter
@Setter
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

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
        this(REnum.A00000, data);
    }

    public R(REnum rEnum) {
        this(rEnum.code, null, rEnum.message);
    }

    public R(REnum rEnum, T data) {
        this(rEnum.code, data, rEnum.message);
    }

    public R(String code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> R<T> success() {
        return success(REnum.A00000, null);
    }

    public static <T> R<T> success(T data) {
        return success(REnum.A00000, data);
    }

    public static <T> R<T> success(REnum rEnum, T data) {
        return success(rEnum.code, data, rEnum.message);
    }

    public static <T> R<T> success(String code, T data, String message) {
        return new R<>(code, data, message);
    }

    public static <T> R<T> fail(REnum rEnum) {
        return fail(rEnum, null);
    }

    public static <T> R<T> fail(REnum rEnum, T data) {
        return fail(rEnum.code, data, rEnum.message);
    }

    public static <T> R<T> fail(String code, T data, String message) {
        return new R<>(code, data, message);
    }
}
