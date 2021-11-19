package com.remember.demo.web.run;

import com.remember.demo.web.design.observer.EventBusCenter;
import com.remember.demo.web.design.observer.EventListener;
import com.remember.demo.web.design.observer.EventListener2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * @author wangjiahao
 * @date 2021/11/19
 */
@Component
public class Runner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventListener eventListener = new EventListener();
        EventListener2 eventListener2 = new EventListener2();
        EventBusCenter.register(eventListener);
        EventBusCenter.register(eventListener2);
    }
}
