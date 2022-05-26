package com.remember.websocket.route.entity;

import cn.hutool.core.lang.UUID;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试实体发送
 *
 * @author wangjiahao
 * @date 2021/11/10
 */
@Data
public class Demo02Message implements Serializable {

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";
    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String ROUTING_KEY = "JAVA.SERVER." + UUID.randomUUID().toString(true) + ".*";

    private String message;

}
