package com.remember.mybatisplus.service.impl;

import com.remember.mybatisplus.MybatisPlusDemoApplicationTests;
import com.remember.mybatisplus.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PersonServiceImplTest extends MybatisPlusDemoApplicationTests {

    @Autowired
    PersonService personService;

    @Test
    void testEncrypt() {
        personService.testEncrypt();
    }
}
