package com.remember.netty.service;

import com.remember.netty.config.NettyProperties;
import com.remember.netty.constant.BaseConstant;
import com.remember.netty.entity.NettyPushMessageBody;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sixiaojie
 * @date 2020-03-30-20:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PushServiceImpl implements PushService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void pushMsgToOne(String userId, String msg) {
        ConcurrentHashMap<String, Channel> userChannelMap = NettyProperties.getUserChannelMap();
        Channel channel = userChannelMap.get(userId);
        if (!Objects.isNull(channel)) {
            // 如果该用户的客户端是与本服务器建立的channel,直接推送消息
            channel.writeAndFlush(new TextWebSocketFrame(msg));
        } else {
            // 发布，给其他服务器消费
            NettyPushMessageBody pushMessageBody = new NettyPushMessageBody();
            pushMessageBody.setUserId(userId);
            pushMessageBody.setMessage(msg);
            redisTemplate.convertAndSend(BaseConstant.PUSH_MESSAGE_TO_ONE, pushMessageBody);
        }

    }

    @Override
    public void pushMsgToAll(String msg) {
        // 发布，给其他服务器消费
        redisTemplate.convertAndSend(BaseConstant.PUSH_MESSAGE_TO_ALL, msg);
//        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
