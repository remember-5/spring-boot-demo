package com.remember.resource.sso.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjiahao
 * @date 2021/2/4
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/info")
    public Authentication info(Authentication authentication) {
        return authentication;
    }


}
