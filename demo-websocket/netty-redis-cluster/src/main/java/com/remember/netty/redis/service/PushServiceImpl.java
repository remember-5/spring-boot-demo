package com.remember.netty.redis.service;

import com.remember.netty.redis.constant.NettyChannelManager;
import com.remember.netty.redis.constant.NettyRedisConstants;
import com.remember.netty.redis.entity.NettyPushMessageBody;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sixiaojie
 * @date 2020-03-30-20:10
 */
@Slf4j
@Service
public class PushServiceImpl implements PushService {

    private final RedisTemplate<String, Object> redisTemplate;

    public PushServiceImpl(@Nullable @Lazy RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void localPush2User(NettyPushMessageBody nettyPushMessageBody) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyChannelManager.getUserChannelMap();
        Channel channel = userChannelMap.get(nettyPushMessageBody.getUserId());
        // 如果该用户的客户端是与本服务器建立的channel,直接推送消息
        channel.writeAndFlush(new TextWebSocketFrame(nettyPushMessageBody.getMessage()));
    }

    @Override
    public void localPushAllUser(NettyPushMessageBody nettyPushMessageBody) {
        NettyChannelManager.getChannelGroup().writeAndFlush(new TextWebSocketFrame(nettyPushMessageBody.getMessage()));
    }

    @Override
    public void pushMsg2User(NettyPushMessageBody nettyPushMessageBody) {
        // 1. 客户端是与本服务器建立的channel,直接推送消息
        if (NettyChannelManager.getUserChannelMap().containsKey(nettyPushMessageBody.getUserId())) {
            Channel channel = NettyChannelManager.getUserChannelMap().get(nettyPushMessageBody.getUserId());
            channel.writeAndFlush(new TextWebSocketFrame(nettyPushMessageBody.getMessage()));
            log.info("用户 {} 在本节点,直接发送消息", nettyPushMessageBody.getUserId());
            return;
        }

        // 2. 发布给其他服务器消费
        if (checkUserOnlineStatus(nettyPushMessageBody.getUserId())) {
            NettyPushMessageBody pushMessageBody = new NettyPushMessageBody();
            pushMessageBody.setUserId(nettyPushMessageBody.getUserId());
            pushMessageBody.setMessage(nettyPushMessageBody.getMessage());
            Objects.requireNonNull(redisTemplate).convertAndSend(NettyRedisConstants.PUSH_MESSAGE_TO_ONE, pushMessageBody);
        } else {
            log.warn("用户 {} 不在线", nettyPushMessageBody.getUserId());
        }


    }

    @Override
    public void pushMsg2AllUser(NettyPushMessageBody nettyPushMessageBody) {
        Objects.requireNonNull(redisTemplate).convertAndSend(NettyRedisConstants.PUSH_MESSAGE_TO_ALL, nettyPushMessageBody.getMessage());
    }

    /**
     * 检测用户在线状态
     * @param userId 用户id
     * @return 在线状态
     */
    private boolean checkUserOnlineStatus(String userId) {
        final Set<String> keys = Objects.requireNonNull(redisTemplate).keys(NettyRedisConstants.WS_CLIENT + "*");

        // 如果没有客户端
        if (Objects.requireNonNull(keys).isEmpty()) {
            log.warn("用户 {} 不在线", userId);
            return false;
        }

        for (String key : keys) {
            if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, userId))) {
                return true;
            }
        }
        return false;
    }



}
