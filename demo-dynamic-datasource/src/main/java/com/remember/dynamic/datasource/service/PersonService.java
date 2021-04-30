package com.remember.dynamic.datasource.service;

import com.remember.dynamic.datasource.entity.Person;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author wangjiahao
 */
public interface PersonService extends IService<Person> {

    /**
     *
     */
    void select();

    /**
     *
     */
    void testInsert();


}
