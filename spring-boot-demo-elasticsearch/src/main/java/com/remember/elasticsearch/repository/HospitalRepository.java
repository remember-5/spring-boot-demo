package com.remember.elasticsearch.repository;

import com.remember.elasticsearch.entity.Hospital;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/12/18
 */
@Component
public interface HospitalRepository extends ElasticsearchRepository<Hospital,Long> {
}
