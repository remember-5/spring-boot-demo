package com.remember.netty.service;


import com.remember.netty.entity.NettyPushMessageBody;

/**
 * @author sixiaojie
 * @date 2020-03-30-17:06
 */
public interface PushService {

    /**
     * 推送给本地channel用户
     * @param nettyPushMessageBody 需要推送的消息
     */
    void localPush2User(NettyPushMessageBody nettyPushMessageBody);

    /**
     * 推送给本地所有用户
     * @param nettyPushMessageBody 需要推送的消息
     */
    void localPushAllUser(NettyPushMessageBody nettyPushMessageBody);

    /**
     * 跨节点推送给指定用户
     *
     * @param nettyPushMessageBody 需要推送的消息
     */
    void pushMsg2User(NettyPushMessageBody nettyPushMessageBody);

    /**
     * 跨节点推送给所有用户
     *
     * @param nettyPushMessageBody 需要推送的消息
     */
    void pushMsg2AllUser(NettyPushMessageBody nettyPushMessageBody);
}
