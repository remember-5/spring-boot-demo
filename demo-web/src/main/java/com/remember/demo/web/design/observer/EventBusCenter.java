package com.remember.demo.web.design.observer;

import com.google.common.eventbus.EventBus;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
public class EventBusCenter {

    private static EventBus eventBus = new EventBus();


    public EventBusCenter() {
    }

    public static EventBus getInstance() {
        return eventBus;
    }

    /**
     * 添加观察者
     *
     * @param obj /
     */
    public static void register(Object obj) {
        eventBus.register(obj);
    }

    /**
     * 移除观察者
     *
     * @param obj /
     */
    public static void unregister(Object obj) {
        eventBus.unregister(obj);
    }

    /**
     * 把消息推给观察者
     *
     * @param obj /
     */
    public static void post(Object obj) {
        eventBus.post(obj);
    }


}
