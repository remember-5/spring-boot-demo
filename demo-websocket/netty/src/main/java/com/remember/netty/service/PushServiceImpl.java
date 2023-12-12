package com.remember.netty.service;

import com.remember.netty.constant.NettyChannelManage;
import com.remember.netty.constant.NettyRedisConstants;
import com.remember.netty.entity.NettyPushMessageBody;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    public void localPush2User(String userId, String message) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyChannelManage.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        // 如果该用户的客户端是与本服务器建立的channel,直接推送消息
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    @Override
    public void localPushAllUser(String message) {
        NettyChannelManage.getChannelGroup().writeAndFlush(new TextWebSocketFrame(message));
    }

    @Override
    public void pushMsg2User(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyChannelManage.getUserChannelMap();
        if (userChannelMap.containsKey(userId)) {
            // 如果该用户的客户端是与本服务器建立的channel,直接推送消息
            Channel channel = userChannelMap.get(userId);
            if (!Objects.isNull(channel)) {
                channel.writeAndFlush(new TextWebSocketFrame(msg));
                log.info("用户在本节点,直接发送消息");
            } else {
                log.warn("用户在本节点,获取channel出错");
            }
            return;
        }

        final Boolean member = redisTemplate.opsForSet().isMember(NettyRedisConstants.REDIS_WEB_SOCKET_USER_SET, userId);
        if (!member) {
            log.warn("用户不在线");
            return;
        }

        log.info("跨节点消息");
        // 发布，给其他服务器消费
        NettyPushMessageBody pushMessageBody = new NettyPushMessageBody();
        pushMessageBody.setUserId(userId);
        pushMessageBody.setMessage(msg);
        redisTemplate.convertAndSend(NettyRedisConstants.PUSH_MESSAGE_TO_ONE, pushMessageBody);
    }

    @Override
    public void pushMsg2AllUser(String msg) {
        // 发布，给其他服务器消费
        redisTemplate.convertAndSend(NettyRedisConstants.PUSH_MESSAGE_TO_ALL, msg);
//        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
