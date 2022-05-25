package com.remember5.elasticsearch7.article;

import com.remember5.elasticsearch7.DemoElasticsearch7Application;
import com.remember5.elasticsearch7.es.EsArticleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;

import javax.annotation.Resource;

/**
 * @author wangjiahao
 * @date 2022/5/25 19:43
 */
@SpringBootTest(classes = DemoElasticsearch7Application.class)
public class TestArticle {

    @Resource
    ArticleRepository articleRepository;

    @Autowired
    ElasticsearchRestTemplate restTemplate;

    @Test
    void testFindArticle() {
        articleRepository.findAll().forEach(e -> System.err.println(e.getContent()));
    }

    @Test
    void createArticleIndex() {
        // 获取IndexOperations对象
        IndexOperations indexOperations = restTemplate.indexOps(EsArticleEntity.class);
        // 查
        boolean exists = indexOperations.exists();
        // 删
        boolean delete = indexOperations.delete();
        // 增
        boolean flag = indexOperations.create();
        // 设置Mapping
        boolean mapping = indexOperations.putMapping();

        EsArticleEntity esArticleEntity = new EsArticleEntity();
        esArticleEntity.setId(1);
        esArticleEntity.setAuthor("wang");
        esArticleEntity.setContent("asdasdasd");
        restTemplate.save(esArticleEntity);


    }


}
