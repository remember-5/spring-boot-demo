package com.remember.netty.pubsub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.remember.netty.constant.NettyChannelManage;
import com.remember.netty.constant.NettyRedisConstants;
import com.remember.netty.entity.NettyPushMessageBody;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sixiaojie
 * @date 2020-08-24-13:47
 */
@Slf4j
public class MessageReceive {

    private RedisTemplate<String, Object> redisTemplate;

    public MessageReceive(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 订阅消息,发送给指定用户
     *
     * @param object /
     */
    public void getMessageToOne(String object) {
        NettyPushMessageBody pushMessageBody = JSON.toJavaObject(JSON.parseObject(object), NettyPushMessageBody.class);
//        FastJsonRedisSerializer serializer = getSerializer(NettyPushMessageBody.class);
//        NettyPushMessageBody pushMessageBody = (NettyPushMessageBody) serializer.deserialize(object.getBytes());
        log.info("订阅消息,发送给指定用户：{}", pushMessageBody.toString());

        // 推送消息
        String message = pushMessageBody.getMessage();
        String userId = pushMessageBody.getUserId();
        final Boolean member = redisTemplate.opsForSet().isMember(NettyRedisConstants.REDIS_WEB_SOCKET_USER_SET, userId);
        if (!member) {
            log.warn("用户不在线");
            return;
        }
        final boolean hasKey = NettyChannelManage.getUserChannelMap().containsKey(userId);
        if (hasKey) {
            ConcurrentHashMap<String, Channel> userChannelMap = NettyChannelManage.getUserChannelMap();
            Channel channel = userChannelMap.get(userId);
            if (!Objects.isNull(channel)) {
                // 如果该用户的客户端是与本服务器建立的channel,直接推送消息
                channel.writeAndFlush(new TextWebSocketFrame(message));
            }
        } else {
            log.warn("未找到userId {} 对应的channel", userId);
        }
    }

    /**
     * 订阅消息，发送给所有用户
     *
     * @param message /
     */
    public void getMessageToAll(String message) {
//        FastJsonRedisSerializer serializer = getSerializer(String.class);
//        String message = (String) serializer.deserialize(object.getBytes());
        log.info("订阅消息，发送给所有用户：{}", message);
        NettyChannelManage.getChannelGroup().writeAndFlush(new TextWebSocketFrame(message));
    }

    private FastJsonRedisSerializer getSerializer(Class clazz) {
        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Class.class);
        FastJsonConfig fastJsonConfig = fastJsonRedisSerializer.getFastJsonConfig();
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setFeatures(Feature.SupportAutoType);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteClassName);
        return fastJsonRedisSerializer;
    }
}
