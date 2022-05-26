package com.remember5.elasticsearch7.logaccess;

import com.remember5.elasticsearch7.DemoElasticsearch7Application;
import com.remember5.elasticsearch7.entity.EsLogAccessEntity;
import com.remember5.elasticsearch7.entity.LogAccessEntity;
import com.remember5.elasticsearch7.mapper.LogAccessMapper;
import com.remember5.elasticsearch7.repository.LogAccessEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2022/5/27 01:00
 */
@SpringBootTest(classes = DemoElasticsearch7Application.class)
public class TestEsLogAccessRepository {

    @Autowired
    private ElasticsearchRestTemplate template;
    @Autowired
    private LogAccessEntityRepository logAccessEntityRepository;
    @Autowired
    private LogAccessMapper logAccessMapper;


    @Test
    public void createIndex() throws Exception {
        IndexOperations indexOperations = template.indexOps(EsLogAccessEntity.class);

        indexOperations.exists();
        indexOperations.delete();
        indexOperations.create();
        indexOperations.putMapping();
    }

    @Test
    public void selectPgDataAndSaveEs(){
        List<LogAccessEntity> all = logAccessEntityRepository.findAll();
        all.forEach(e->{
            EsLogAccessEntity esLogAccessEntity = logAccessMapper.toEsEntity(e);
            template.save(esLogAccessEntity);
        });

    }



}
