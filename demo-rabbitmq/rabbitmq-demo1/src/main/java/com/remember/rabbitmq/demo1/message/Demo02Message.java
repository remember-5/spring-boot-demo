package com.remember.rabbitmq.demo1.message;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试实体发送
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo02Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String ROUTING_KEY = "#.SPRING.RABBIT";

    public static final String KEY_1 = "A.SPRING.RABBIT";
    public static final String KEY_2 = "A.RABBIT.SPRING";

    private String message;

}
