package com.remember.elasticsearch.repository;


import com.remember.elasticsearch.entity.CustomerComplain;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wangjiahao
 * @date 2020/3/26
 */
public interface CustomerComplainRepository extends ElasticsearchRepository<CustomerComplain, Long> {

//    /**
//     * 根据年龄区间查询
//     *
//     * @param min 最小值
//     * @param max 最大值
//     * @return 满足条件的用户列表
//     */
//    List<HotWireKb> findByIdBetween(Integer min, Integer max);
}
