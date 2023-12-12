/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.netty.runner;

import com.remember.netty.constant.NettyRedisConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @author wangjiahao
 * @date 2023/3/27 15:28
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void run(String... args) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5))) {
            log.info("删除Redis中未关闭的客户端连接信息，Delete redis key: {}", NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5);
            redisTemplate.delete(NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5);
        }
    }

    @PreDestroy
    public void destroy() {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5))) {
            log.info("删除Redis中未关闭的客户端连接信息，Delete redis key: {}", NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5);
            redisTemplate.delete(NettyRedisConstants.WS_CLIENT + NettyRedisConstants.ADDRESS_MD5);
        }
    }


}
