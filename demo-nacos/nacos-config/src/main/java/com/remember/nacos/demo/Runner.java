package com.remember.nacos.demo;

import com.remember.nacos.demo.nacosconfig.NacosConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2021/10/22
 */
@Slf4j
@Configuration
public class Runner implements ApplicationRunner {
    @Autowired
    NacosConfig nacosConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("服务加载完成,读取name {}", nacosConfig.getW());

    }
}
