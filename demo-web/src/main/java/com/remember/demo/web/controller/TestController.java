package com.remember.demo.web.controller;

import com.remember.demo.web.design.observer.EventBusCenter;
import com.remember.demo.web.design.observer.NotifyEvent;
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


    @GetMapping("observer")
    public String test2() {
        EventBusCenter.post(new NotifyEvent("13372817283", "123@qq.com", "666"));
        return "success";
    }

}
