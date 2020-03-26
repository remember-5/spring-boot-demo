package com.remember.elasticsearch.repository;

import com.remember.elasticsearch.entity.FilmEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/3/26
 */
@Component
public interface FilmRepository extends ElasticsearchRepository<FilmEntity,Long> {
}
