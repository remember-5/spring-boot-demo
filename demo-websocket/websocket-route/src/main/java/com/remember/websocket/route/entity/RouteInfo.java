package com.remember.websocket.route.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author fly
 * @date 2021/12/16 18:13
 */
@Data
public class RouteInfo implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 服务id
     */
    private String serverId;

    /**
     * 消息队列key
     */
    private String routingKey;

    /**
     * 这个用户接受的消息List
     */
    private List<Message> messages;

}
