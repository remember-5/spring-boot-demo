package com.remember.dynamic.datasource.runner;

import com.remember.dynamic.datasource.service.LogAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2022/4/18 11:43
 */
@Configuration
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    private final LogAccessService logAccessService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logAccessService.testBatchInsert();
    }
}
