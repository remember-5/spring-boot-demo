package com.remember.dubbo.provide.service;

import com.remember.common.nacos.SayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wangjiahao
 * @date 2021/10/22
 */
@Slf4j
@DubboService
public class SayServiceImpl implements SayService {
    @Override
    public String sayHelloByName(String name) {
        log.info("nacos-dubbo-provide: sayHelloByName : name= {}", name);
        return name+",hello!";
    }
}
