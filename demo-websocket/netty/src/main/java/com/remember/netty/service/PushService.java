package com.remember.netty.service;


/**
 * @author sixiaojie
 * @date 2020-03-30-17:06
 */
public interface PushService {
    /**
     * 推送给指定用户
     *
     * @param userId user id
     * @param msg    message
     */
    void pushMsgToOne(String userId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg message
     */
    void pushMsgToAll(String msg);
}
