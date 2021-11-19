package com.remember.demo.web.design.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
public class EventBusDemoTest {
    public static void main(String[] args) {
        EventListener eventListener = new EventListener();
        EventListener2 eventListener2 = new EventListener2();
        EventBusCenter.register(eventListener);
        EventBusCenter.register(eventListener2);
        EventBusCenter.post(new NotifyEvent("13372817283", "123@qq.com", "666"));

    }
}
