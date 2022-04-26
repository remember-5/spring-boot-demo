package com.remember.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2022/4/26 10:23
 */

@RequestMapping("/api/{version}/user")
@RestController
public class UserV1Controller {

    @GetMapping("/test")
    public String test() {
        return "version1";
    }
    @GetMapping("/extend")
    public String extendTest() {
        return "user v1 extend";
    }
}
