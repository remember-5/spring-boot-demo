package com.remember.websocket.cluster.service.impl;

import com.remember.websocket.cluster.service.IRedisSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangjiahao
 * @date 2021/12/14
 */
@Slf4j
@Service
public class SimulationRedisSessionServiceImpl implements IRedisSessionService {

    @Autowired
    private RedisTemplate<String, String> template;

    /**
     * 在缓存中保存用户和websocket sessionid的信息
     * @param name
     * @param wsSessionId
     */
    @Override
    public void add(String name, String wsSessionId) {
        BoundValueOperations<String,String> boundValueOperations = template.boundValueOps(name);
        boundValueOperations.set(wsSessionId,24 * 3600, TimeUnit.SECONDS);
    }

    /**
     * 从缓存中删除用户的信息
     * @param name
     */
    @Override
    public boolean del(String name) {
        return template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] rawKey = template.getStringSerializer().serialize(name);
                return connection.del(rawKey) > 0;
            }
        }, true);
    }

    /**
     * 根据用户id获取用户对应的sessionId值
     * @param name
     * @return
     */
    @Override
    public String get(String name) {
        BoundValueOperations<String,String> boundValueOperations = template.boundValueOps(name);
        return boundValueOperations.get();
    }
}
