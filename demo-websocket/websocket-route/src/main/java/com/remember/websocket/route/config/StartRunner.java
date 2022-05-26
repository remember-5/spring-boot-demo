package com.remember.websocket.route.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author fly
 * @date 2021/12/16 17:58
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartRunner implements ApplicationRunner {

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments /
     */
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        String s = UUID.randomUUID().toString(true);
//        WebSocketConstant.SERVER_ID += s;
//        Demo02Message.ROUTING_KEY = Demo02Message.ROUTING_KEY  + s + ".*";
//        log.info(WebSocketConstant.SERVER_ID);
//        log.info("--------------------主机编号已生成---------------------");
    }
}
