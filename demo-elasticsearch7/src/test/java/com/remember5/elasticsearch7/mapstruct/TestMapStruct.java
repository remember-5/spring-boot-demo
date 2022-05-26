package com.remember5.elasticsearch7.mapstruct;

import com.remember5.elasticsearch7.DemoElasticsearch7Application;
import com.remember5.elasticsearch7.entity.EsLogAccessEntity;
import com.remember5.elasticsearch7.entity.LogAccessEntity;
import com.remember5.elasticsearch7.mapper.LogAccessMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjiahao
 * @date 2022/5/27 00:34
 */
@SpringBootTest(classes = DemoElasticsearch7Application.class)
public class TestMapStruct {

    @Autowired
    LogAccessMapper mapper;

    @Test
    public void testConverter() throws Exception {
        LogAccessEntity entity = new LogAccessEntity();
        entity.setId("1");
        entity.setVMethod("test");

        EsLogAccessEntity esEntity = mapper.toEsEntity(entity);
        System.err.println(esEntity.getId());
        System.err.println(esEntity.getVMethod());

    }
}
