package com.remember.elasticsearch.template;

import com.remember.elasticsearch.ElasticSearchDemoApplicationTests;
import com.remember.elasticsearch.entity.HotWireKb;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @author wangjiahao
 * @date 2020/3/26
 */
public class TemplateTest extends ElasticSearchDemoApplicationTests {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    /**
     * 测试 ElasticTemplate 创建 index
     */
    @Test
    public void testCreateIndex() {
        // 创建索引，会根据Item类的@Document注解信息来创建
        esTemplate.createIndex(HotWireKb.class);

        // 配置映射，会根据Item类中的id、Field等字段来自动完成映射
        esTemplate.putMapping(HotWireKb.class);
    }

    /**
     * 测试 ElasticTemplate 删除 index
     */
    @Test
    public void testDeleteIndex() {
        esTemplate.deleteIndex(HotWireKb.class);
    }
}
