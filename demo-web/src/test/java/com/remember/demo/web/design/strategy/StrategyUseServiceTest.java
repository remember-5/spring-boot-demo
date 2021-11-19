package com.remember.demo.web.design.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@Slf4j
@SpringBootTest
public class StrategyUseServiceTest {

    @Resource
    StrategyUseService strategyUseService;

    @Test
    void test(){
        strategyUseService.resolveFile(FileTypeResolveEnum.File_A_RESOLVE,"cesji");
    }
}