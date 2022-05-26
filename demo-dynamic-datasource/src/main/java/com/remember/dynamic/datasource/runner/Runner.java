package com.remember.dynamic.datasource.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2022/4/18 11:43
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("启动成功");
    }
}
