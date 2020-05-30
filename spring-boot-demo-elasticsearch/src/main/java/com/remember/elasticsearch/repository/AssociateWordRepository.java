package com.remember.elasticsearch.repository;

import com.remember.elasticsearch.entity.AssociateWord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/5/30
 */
@Component
public interface AssociateWordRepository extends ElasticsearchRepository<AssociateWord,Long> {
}
