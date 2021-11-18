package com.remember.rabbitmq.demo1.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo04Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_04_A";
    public static final String EXCHANGE = "EXCHANGE_DEMO_04";
    public static final String HEADER_KEY = "color";
    public static final String HEADER_VALUE = "red";

    private String message;
}
