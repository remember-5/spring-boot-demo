package com.remember.demo.web.design.template;

/**
 * @author wangjiahao
 * @date 2021/11/19
 */
public class Coffee extends CaffeineBeverage{
    @Override
    void brew() {
        System.out.println("Adding Sugar and Milk...");
    }

    @Override
    void addCondiments() {
        System.out.println("Dripping Coffee through filter...");
    }
}
