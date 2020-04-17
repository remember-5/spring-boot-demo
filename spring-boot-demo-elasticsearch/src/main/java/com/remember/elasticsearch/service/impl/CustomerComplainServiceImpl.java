package com.remember.elasticsearch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.elasticsearch.entity.CustomerComplain;
import com.remember.elasticsearch.mapper.CustomerComplainMapper;
import com.remember.elasticsearch.service.CustomerComplainService;
import org.springframework.stereotype.Service;

/**
 * @author wangjiahao
 * @date 2020/3/25
 */
@Service
public class CustomerComplainServiceImpl extends ServiceImpl<CustomerComplainMapper, CustomerComplain> implements CustomerComplainService {

}
