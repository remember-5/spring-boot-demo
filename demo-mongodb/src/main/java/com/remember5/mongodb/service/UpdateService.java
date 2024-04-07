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

import com.mongodb.client.result.UpdateResult;
import com.remember5.mongodb.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateService {
    /**
     * 设置集合名称
     */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】
     *
     * @return 执行更新的结果
     */
    public Object update() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(30);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 创建更新对象,并设置更新的内容
        Update update = new Update().set("age", 33).set("name", "zhangsansan");
        // 执行更新，如果没有找到匹配查询的文档，则创建并插入一个新文档
        UpdateResult result = mongoTemplate.upsert(query, update, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "匹配到" + result.getMatchedCount() + "条数据,对第一条数据进行了更改";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }

    /**
     * 更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】
     *
     * @return 执行更新的结果
     */
    public Object updateFirst() {
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is("zhangsan");
        // 创建查询对象，然后将条件对象添加到其中，并设置排序
        Query query = new Query(criteria).with(Sort.by("age").ascending());
        // 创建更新对象,并设置更新的内容
        Update update = new Update().set("age", 30).set("name", "zhangsansan");
        // 执行更新
        UpdateResult result = mongoTemplate.updateFirst(query, update, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }

    /**
     * 更新【匹配查询】到的【文档数据集合】中的【所有数据】
     *
     * @return 执行更新的结果
     */
    public Object updateMany() {
        // 创建条件对象
        Criteria criteria = Criteria.where("age").gt(28);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 设置更新字段和更新的内容
        Update update = new Update().set("age", 29).set("salary", "1999");
        // 执行更新
        UpdateResult result = mongoTemplate.updateMulti(query, update, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "总共匹配到" + result.getMatchedCount() + "条数据,修改了" + result.getModifiedCount() + "条数据";
        log.info("更新结果：{}", resultInfo);
        return resultInfo;
    }

}
