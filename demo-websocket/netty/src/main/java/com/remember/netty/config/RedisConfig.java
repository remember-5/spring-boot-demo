package com.remember.netty.config;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.remember.netty.constant.RedisConstants;
import com.remember.netty.pubsub.MessageReceive;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author sixiaojie
 * @date 2020-08-24-13:38
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 RedisConnection 工厂
        template.setConnectionFactory(redisConnectionFactory);

        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }

    /**
     * redis消息监听器容器
     * 可以添加多个监听不同话题的redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定，该消息监听器
     * 通过反射技术调用消息订阅处理器的相关方法进行一些业务处理
     * @param redisConnectionFactory /
     * @param listenerAdapter1 /
     * @param listenerAdapter2 /
     * @return /
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                                   MessageListenerAdapter listenerAdapter1,
                                                   MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

        // 订阅频道(发送给指定用户)
        redisMessageListenerContainer.addMessageListener(listenerAdapter1, new PatternTopic(RedisConstants.PUSH_MESSAGE_TO_ONE));
        // 订阅频道(发送给所有用户)
        redisMessageListenerContainer.addMessageListener(listenerAdapter2, new PatternTopic(RedisConstants.PUSH_MESSAGE_TO_ALL));

        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        FastJsonConfig fastJsonConfig = fastJsonRedisSerializer.getFastJsonConfig();
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setFeatures(Feature.SupportAutoType);
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteClassName);
        redisMessageListenerContainer.setTopicSerializer(fastJsonRedisSerializer);
        return redisMessageListenerContainer;
    }


    /**
     * 表示监听一个频道（发送给指定用户）
     */
    @Bean
    public MessageListenerAdapter listenerAdapter1(MessageReceive messageReceive) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceive ”
        return new MessageListenerAdapter(messageReceive, "getMessageToOne");
    }

    /**
     * 表示监听一个频道（发送给所有用户）
     */
    @Bean
    public MessageListenerAdapter listenerAdapter2(MessageReceive messageReceive) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“MessageReceive ”
        return new MessageListenerAdapter(messageReceive, "getMessageToAll");
    }

}
