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

import com.mongodb.client.result.DeleteResult;
import com.remember5.mongodb.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2024/3/31 17:29
 */
@Slf4j
@Service
@RequiredArgsConstructor

public class RemoveService {
    /**
     * 设置集合名称
     */
    private static final String COLLECTION_NAME = "users";

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 删除集合中【符合条件】的【一个]或[多个】文档
     *
     * @return 删除用户信息的结果
     */
    public Object remove() {
        // 设置查询条件参数
        int age = 30;
        String sex = "男";
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age).and("sex").is(sex);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档信息
        DeleteResult result = mongoTemplate.remove(query, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除 " + result.getDeletedCount() + " 条文档信息";
        log.info(resultInfo);
        return resultInfo;
    }

    /**
     * 删除【符合条件】的【单个文档】，并返回删除的文档。
     *
     * @return 删除的用户信息
     */
    public Object findAndRemove() {
        // 设置查询条件参数
        String name = "zhangsansan";
        // 创建条件对象
        Criteria criteria = Criteria.where("name").is(name);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的第一条文档,并返回删除的文档信息
        User result = mongoTemplate.findAndRemove(query, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + result;
        log.info(resultInfo);
        return result;
    }

    /**
     * 删除【符合条件】的【全部文档】，并返回删除的文档。
     *
     * @return 删除的全部用户信息
     */
    public Object findAllAndRemove() {
        // 设置查询条件参数
        int age = 22;
        // 创建条件对象
        Criteria criteria = Criteria.where("age").is(age);
        // 创建查询对象，然后将条件对象添加到其中
        Query query = new Query(criteria);
        // 执行删除查找到的匹配的全部文档,并返回删除的全部文档信息
        List<User> resultList = mongoTemplate.findAllAndRemove(query, User.class, COLLECTION_NAME);
        // 输出结果信息
        String resultInfo = "成功删除文档信息，文档内容为：" + resultList;
        log.info(resultInfo);
        return resultList;
    }


}
