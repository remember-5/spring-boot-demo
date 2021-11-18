package com.remember.rabbitmq.demo1.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo03Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_03";

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";

    private String message;
}
