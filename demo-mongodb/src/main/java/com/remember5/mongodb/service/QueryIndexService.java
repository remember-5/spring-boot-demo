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

import com.mongodb.client.ListIndexesIterable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class QueryIndexService {
    /** 设置集合名称 */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 获取当前【集合】对应的【所有索引】的【名称列表】
     *
     * @return 当前【集合】所有【索引名称列表】
     */
    public Object getIndexAll() {
        // 获取集合中所有列表
        ListIndexesIterable<Document> indexList = mongoTemplate.getCollection(COLLECTION_NAME).listIndexes();
        // 创建字符串集合
        List<Document> list = new ArrayList<>();
        // 获取集合中全部索引信息
        for (Document document : indexList) {
            log.info("索引列表：{}",document);
            list.add(document);
        }
        return list;
    }
}
