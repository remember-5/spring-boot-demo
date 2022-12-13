package com.remember.netty.service;


/**
 * @author sixiaojie
 * @date 2020-03-30-17:06
 */
public interface PushService {

    void localPush2User(String userId, String message);

    void localPushAllUser(String message);

    /**
     * 推送给指定用户
     *
     * @param userId user id
     * @param msg    message
     */
    void pushMsg2User(String userId, String msg);

    /**
     * 推送给所有用户
     *
     * @param msg message
     */
    void pushMsg2AllUser(String msg);
}
