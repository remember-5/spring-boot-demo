package com.remember.demo.web.design.observer;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Slf4j
public class EventListener {


    @Subscribe
    public void handle(NotifyEvent notifyEvent){
        System.out.println("发送IM消息" + notifyEvent.getImNo());
        System.out.println("发送短信消息" + notifyEvent.getMobileNo());
        System.out.println("发送Email消息" + notifyEvent.getEmailNo());
    }

}
