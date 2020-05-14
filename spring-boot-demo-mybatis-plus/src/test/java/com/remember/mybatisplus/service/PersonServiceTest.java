package com.remember.mybatisplus.service;


import com.remember.mybatisplus.MybatisPlusDemoApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PersonServiceTest extends MybatisPlusDemoApplicationTests {

    @Autowired
    PersonService personService;

    /**
     * 测试一下事物的执行顺序
     */
    @Test
    void test(){
        personService.test();
    }

    /**
     * 测试一下事物的执行顺序
     */
    @Test
    void test1(){
    }


}