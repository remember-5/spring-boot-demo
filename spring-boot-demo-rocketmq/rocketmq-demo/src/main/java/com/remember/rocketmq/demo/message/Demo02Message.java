package com.remember.rocketmq.demo.message;

import lombok.Data;

/**
 * @author wangjihao
 * @date 2020/12/2
 */
@Data
public class Demo02Message {

    public static final String TOPIC = "DEMO_02";

    private Integer id;
}
