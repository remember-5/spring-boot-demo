package com.remember.dynamic.datasource.service.impl;

import com.remember.dynamic.datasource.SpringBootDemoDynamicDatasourceApplicationTests;
import com.remember.dynamic.datasource.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImplTest extends SpringBootDemoDynamicDatasourceApplicationTests {

    @Autowired
    PersonService personService;

    @Test
    public void select() {
        personService.select();
    }

    @Test
    public void insert() {
        personService.testInsert();
    }
}