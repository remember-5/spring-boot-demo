package com.remember.netty.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author fly
 * @description
 * @date 2021/4/15 15:19
 */
@Data
public class SocketMessage {
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 发送目标
     */
    private Set<String> users;

    public SocketMessage() {
    }

    public SocketMessage(String msg, Set<String> users) {
        this.msg = msg;
        this.users = users;
    }

    public SocketMessage(String msg) {
        this.msg = msg;
    }
}
