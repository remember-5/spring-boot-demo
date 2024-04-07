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

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:30
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CreateIndexService {

    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建升序索引
     *
     * @return 索引信息
     */
    public Object createAscendingIndex() {
        // 设置字段名称
        String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.ascending(field));
    }

    /**
     * 创建降序索引
     *
     * @return 索引信息
     */
    public Object createDescendingIndex() {
        // 设置字段名称
        String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.descending(field));
    }

    /**
     * 创建升序复合索引
     *
     * @return 索引信息
     */
    public Object createCompositeIndex() {
        // 设置字段名称
        String field1 = "name";
        String field2 = "age";
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.ascending(field1, field2));
    }

    /**
     * 创建文字索引
     *
     * @return 索引信息
     */
    public Object createTextIndex() {
        // 设置字段名称
        String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.text(field));
    }

    /**
     * 创建哈希索引
     *
     * @return 索引信息
     */
    public Object createHashIndex() {
        // 设置字段名称
        String field = "name";
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.hashed(field));
    }

    /**
     * 创建升序唯一索引
     *
     * @return 索引信息
     */
    public Object createUniqueIndex() {
        // 设置字段名称
        String indexName = "name";
        // 配置索引选项
        IndexOptions options = new IndexOptions();
        // 设置为唯一索引
        options.unique(true);
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.ascending(indexName), options);
    }

    /**
     * 创建局部索引
     *
     * @return 索引信息
     */
    public Object createPartialIndex() {
        // 设置字段名称
        String field = "name";
        // 配置索引选项
        IndexOptions options = new IndexOptions();
        // 设置过滤条件
        options.partialFilterExpression(Filters.exists("name", true));
        // 创建索引
        return mongoTemplate.getCollection(COLLECTION_NAME).createIndex(Indexes.ascending(field), options);
    }

}
