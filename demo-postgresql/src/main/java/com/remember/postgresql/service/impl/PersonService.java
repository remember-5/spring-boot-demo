package com.remember.postgresql.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remember.postgresql.entity.Person;

import java.util.List;

public interface PersonService extends IService<Person> {


    int updateBatch(List<Person> list);

    int batchInsert(List<Person> list);

    int insertOrUpdate(Person record);

    int insertOrUpdateSelective(Person record);

}
