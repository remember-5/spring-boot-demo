package com.remember.websocket.route.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fly
 * @date 2021/12/16 18:34
 */
@Data
public class Message implements Serializable {

    /**
     * 发送消息的用户
     */
    private String name;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 发送目标
     */
    private String sendToUser;

    /**
     * 发送消息类型 0 群发 1 单发....
     */
    private Integer type;
}
