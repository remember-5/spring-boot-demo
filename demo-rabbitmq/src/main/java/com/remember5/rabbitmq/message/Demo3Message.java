package com.remember5.rabbitmq.message;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo3Message {

    public static final String EXCHENGE = "EXCAHNGE_DEMO_03";

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";

    private String message;
}
