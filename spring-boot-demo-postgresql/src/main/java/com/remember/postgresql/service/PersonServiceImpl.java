package com.remember.postgresql.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.postgresql.entity.Person;
import com.remember.postgresql.mapper.PersonMapper;
import com.remember.postgresql.service.impl.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService{

    @Override
    public int updateBatch(List<Person> list) {
        return baseMapper.updateBatch(list);
    }
    @Override
    public int batchInsert(List<Person> list) {
        return baseMapper.batchInsert(list);
    }
    @Override
    public int insertOrUpdate(Person record) {
        return baseMapper.insertOrUpdate(record);
    }
    @Override
    public int insertOrUpdateSelective(Person record) {
        return baseMapper.insertOrUpdateSelective(record);
    }
}
