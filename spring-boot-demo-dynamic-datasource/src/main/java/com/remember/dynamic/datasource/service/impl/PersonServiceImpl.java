package com.remember.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.dynamic.datasource.entity.Person;
import com.remember.dynamic.datasource.mapper.PersonMapper;
import com.remember.dynamic.datasource.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * @author wangjiahao
 */
@Service
@RequiredArgsConstructor
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService{

    private final DataSource dataSource;

    @DS("slave")
    @Override
    public void select() {
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.getCurrentDataSources().keySet();

        Person person = this.baseMapper.selectById(1);
        System.err.println(person);
    }

    @DS("master")
    @Override
    public void testInsert() {
        Person person = new Person();
        person.setName("wangjiahao");
        this.baseMapper.insert(person);
    }



}
