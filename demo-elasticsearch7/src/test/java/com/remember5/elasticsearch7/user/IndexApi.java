package com.remember5.elasticsearch7.user;

import com.remember5.elasticsearch7.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

/**
 * 索引增删改查
 *
 * @author Max_Qiu
 */
@SpringBootTest
public class IndexApi {
    @Autowired
    private ElasticsearchRestTemplate template;

    /**
     * 创建索引
     */
    @Test
    void createIndex() {
        IndexOperations indexOperations = template.indexOps(User.class);
        exists(indexOperations);
        delete(indexOperations);
        create(indexOperations);
        mapping(indexOperations);
    }

    void exists(IndexOperations indexOperations) {
        boolean exists = indexOperations.exists();
        System.out.println("索引是否存在：" + exists);
    }

    void delete(IndexOperations indexOperations) {
        boolean delete = indexOperations.delete();
        System.out.println("索引是否删除：" + delete);
    }

    void create(IndexOperations indexOperations) {
        boolean flag = indexOperations.create();
        System.out.println("索引创建结果：" + flag);
    }

    void mapping(IndexOperations indexOperations) {
        boolean mapping = indexOperations.putMapping();
        System.out.println("映射修改结果：" + mapping);
    }
}
