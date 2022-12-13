package com.remember.netty.controller;

import com.remember.netty.service.PushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sixiaojie
 * @date 2020-03-30-20:08
 */
@Slf4j
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class PushController {

    private final PushService pushService;

    @PostMapping("/localPush2User")
    public void localPush2User(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        pushService.localPush2User(userId, msg);
    }

    @PostMapping("/localPushAllUser")
    public void localPushAllUser(@RequestParam("msg") String msg) {
        pushService.localPushAllUser(msg);
    }

    @PostMapping("/pushMsg2User")
    public void pushMsg2User(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        pushService.pushMsg2User(userId, msg);
    }

    @PostMapping("/pushMsg2AllUser")
    public void pushMsg2AllUser(@RequestParam("msg") String msg) {
        pushService.pushMsg2AllUser(msg);
    }

}
