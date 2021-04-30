package com.remember.rocketmq.demo.message;

import lombok.Data;

/**
 * @author wangjiahao
 * @date 2020/12/2
 */
@Data
public class Demo01Message {
    public static final String TOPIC = "DEMO_01";

    private Integer id;
}
