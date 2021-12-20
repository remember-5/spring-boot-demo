package com.remember.common.entity;

import java.util.HashMap;

/**
 * @author wangjiahao
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;
    private static final String MESSAGE = "message";
    private static final String DATA = "data";
    private static final String CODE = "code";

    public R() {
        this.put(DATA, "");
    }

    public R message(String message) {
        this.put(MESSAGE, message);
        return this;
    }

    public R data(Object data) {
        this.put(DATA, data);
        return this;
    }

    public R code(String code) {
        this.put(CODE, code);
        return this;
    }

    public R resultEnum(ResultEnum resultEnum) {
        this.put(CODE, resultEnum.code);
        this.put(MESSAGE, resultEnum.message);
        return this;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get(MESSAGE));
    }

    public Object getData() {
        return get(DATA);
    }
}
