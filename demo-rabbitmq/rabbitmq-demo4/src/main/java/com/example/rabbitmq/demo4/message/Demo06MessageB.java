package com.example.rabbitmq.demo4.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2021/11/17
 */
@Data
public class Demo06MessageB implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_06_B";

    public static final String EXCHANGE = "EXCHANGE_DEMO_06_B";

    public static final String ROUTING_KEY = "ROUTING_KEY_06_B";

    private String message;

}
