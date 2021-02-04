package com.remember.resource.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2021/2/4
 */
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @RequestMapping("hello")
    public String hello(){
        return "world";
    }


}
