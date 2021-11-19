package com.remember.demo.web.design.template;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
public class Tea extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("Adding Lemon...");
    }

    @Override
    void addCondiments() {
        System.out.println("Steeping the tea...");

    }
}
