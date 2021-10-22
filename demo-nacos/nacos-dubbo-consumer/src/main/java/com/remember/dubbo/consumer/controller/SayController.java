package com.remember.dubbo.consumer.controller;

import com.remember.common.nacos.SayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2021/10/22
 */
@Slf4j
@RestController
@RequestMapping("/demo/say")
public class SayController {
    @DubboReference
    private SayService sayService;

    @GetMapping("/sayHello")
    public ResponseEntity<String> sayHello(@RequestParam("name") String name) {
        log.info("nacos-dubbo-consumer: sayHello : name= {}", name);
        return ResponseEntity.ok(sayService.sayHelloByName(name));
    }



}
