package com.remember.demo.web.design.duty;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class ChainPatternDemoTest {


    @Resource
    ChainPatternDemo chainPatternDemo;

    @Test
    void test(){
        chainPatternDemo.exec(null,null);
    }

}