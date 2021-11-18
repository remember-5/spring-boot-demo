package com.remember.demo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjihao
 * @date 2020/12/21
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("${api.a}")
    public String test() {
        return "aaa";
    }

    @GetMapping("${api.b}")
    public String test1() {
        return "bbb";
    }
}
