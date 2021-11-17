package com.remember5.rabbitmq.message;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo01Message {

    public static final String EXCHANGE = "EXCHANGE_DEMO_01";

    public static final String QUEUE = "QUEUE_DEMO_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_01";

    private String message;
}
