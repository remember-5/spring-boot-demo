package com.remember5.demopackage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remember5.demopackage.entity.Person;

/**
 * @author wangjiahao
 */
public interface PersonService extends IService<Person> {

    /**
     * test
     */
    void test();

    /**
     * 测试加密
     */
    void testEncrypt();


}

