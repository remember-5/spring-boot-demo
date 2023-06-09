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
package com.remember.redis.service.impl;

import com.remember.redis.service.RedissonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author wangjiahao
 * @date 2023/6/9 16:36
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RedissonServiceImpl implements RedissonService {

    private final RedisLockRegistry redisLockRegistry;
    private final RedissonClient redissonClient;

    @Override
    public String lock()  {
        log.info("线程 {} 启动了", Thread.currentThread().getName());
        RLock lock = redissonClient.getLock("Lock");
        boolean isLocked = lock.tryLock();
        if (isLocked) {
            try {
                if (lock.isLocked()) {
                    return "成功了";
                } else {
                    return "失败";
                }
            } finally {
                lock.unlock();
            }

        }
        return "没拿到锁";
    }
}
