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
package com.remember5.mongodb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:32
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RemoveIndexService {

    @Resource
    private MongoTemplate mongoTemplate;

    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "users";

    /**
     * 根据索引名称移除索引
     */
    public void removeIndex() {
        // 设置索引名称
        String indexName = "name_1";
        // 删除集合中某个索引
        mongoTemplate.getCollection(COLLECTION_NAME).dropIndex(indexName);
    }

    /**
     * 移除全部索引
     */
    public void removeIndexAll() {
        // 删除集合中全部索引
        mongoTemplate.getCollection(COLLECTION_NAME).dropIndexes();
    }
}
