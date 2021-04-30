package com.remember.elasticsearch.repository;

/**
 * @author wangjiahao
 * @date 2020/3/26
 */

import com.remember.elasticsearch.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo, Long> {
}
