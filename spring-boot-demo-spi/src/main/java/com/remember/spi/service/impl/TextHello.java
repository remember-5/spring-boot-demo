package com.remember.spi.service.impl;

import com.remember.spi.service.HelloInterface;

/**
 * @author wangjiahao
 * @date 2020/4/20
 */
public class TextHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("text hello");
    }
}
