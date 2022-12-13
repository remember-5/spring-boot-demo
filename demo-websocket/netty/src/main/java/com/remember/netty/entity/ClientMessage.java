package com.remember.netty.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2022/6/27 17:31
 */
@Data
public class ClientMessage implements Serializable {
    /**
     * 消息类型：  1 单聊  2,群聊
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String msgInfo;
    /**
     * 消息发送方 （目前只在单聊中体现，【群聊暂时没有分组处理】）
     */
    private String to;
}
