package com.remember.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/4/29
 */
@Component
public class RedisConfiguration {

    /**
     * 创建 RedisTemplate Bean，使用 JSON 序列化方式
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建 RedisTemplate 对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置 RedisConnection 工厂
        template.setConnectionFactory(factory);

        // 使用 String 序列化方式，序列化 KEY 。
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 使用 JSON 序列化方式（库是 Jackson ），序列化 VALUE 。
        template.setValueSerializer(RedisSerializer.json());
        template.setHashValueSerializer(RedisSerializer.json());
        return template;
    }

    /**
     * 连接工厂
     *
     * @param redisson /
     * @return /
     */
//    @Bean
//    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
//        return new RedissonConnectionFactory(redisson);
//    }

//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
//        Config config = Config.fromYAML(configFile.getInputStream());
//        // 使用单体服务
//        config.useSingleServer();
//        // json进行编码
//        config.setCodec(new JsonJacksonCodec());
//        return Redisson.create(config);
//    }

    /**
     * 分布式锁
     * RedisLockRegistry相当于一个锁的管理器，所有的分布式锁都可以从中获取，
     * 如上定义，锁的键名为 “redis-lock: 你定义的 key”，超时时间也可以自己设定，默认超时时间是 60s。
     * RedisLockRegistry是基于 Redis 的 setnx 和ReentrantLock可重入锁实现
     *
     * @param redisConnectionFactory factory
     * @return /
     */
//    @Bean(name = "redisLockRegistry", destroyMethod = "destroy")
//    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
//        // 默认失效时间
//        long defaultExpireTime = 60000L;
//        // 默认前缀
//        String defaultPrefixKey = "lock";
//        return new RedisLockRegistry(redisConnectionFactory, defaultPrefixKey);
//    }
}
