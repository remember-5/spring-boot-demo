package com.remember.demo.web.controller;

import com.remember.demo.web.annotation.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2022/4/26 10:23
 */


@RequestMapping("/api/{version}/user")
@RestController
@ApiVersion(2)
public class UserV2Controller {

    @GetMapping("/test")
    public String test() {
        return "user v2 test";
    }
}
