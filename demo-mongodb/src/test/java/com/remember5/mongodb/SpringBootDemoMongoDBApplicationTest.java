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
package com.remember5.mongodb;

import cn.hutool.core.util.IdUtil;
import com.remember5.mongodb.entity.ChatContent;
import com.remember5.mongodb.entity.GptChat;
import com.remember5.mongodb.repository.GptRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:20
 */
@SpringBootTest
class SpringBootDemoMongoDBApplicationTest {
    @Resource
    private MongoTemplate mongoTemplate;
    @Resource
    private GptRepository gptRepository;


    void save() {
        GptChat gptChat = new GptChat().setId(IdUtil.fastSimpleUUID())
                .setChannelId(IdUtil.fastSimpleUUID())
                .setUserId(IdUtil.fastSimpleUUID())
                .setCreateTime(new Date());
        final List<ChatContent> objects = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            objects.add(new ChatContent().setId(i + 1).setType(i % 2 == 0 ? "q" : "a").setText("hello" + i).setCreateTime(new Date()));
        }
        gptChat.setContent(objects);
        gptRepository.save(gptChat);
    }

    @Test
    void testInit() {
        save();
        find();
    }

    void find() {
        final List<GptChat> all = gptRepository.findAll();
        all.forEach(System.out::println);
        System.err.println(all.get(0));

    }
}
