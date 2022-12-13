package com.remember.netty.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wangjiahao
 * @date 2022/6/27 17:32
 */
@Data
public class ServerMessage implements Serializable {

    /**
     * 消息发送方
     */
    private String from;
    /**
     * 消息内容
     */
    private String msgInfo;

    /**
     * 时间
     */
    private String date;

    public ServerMessage(String from, String msgInfo) {
        this.from = from;
        this.msgInfo = msgInfo;
        this.date = LocalDateTime.now().toString();
    }
}
