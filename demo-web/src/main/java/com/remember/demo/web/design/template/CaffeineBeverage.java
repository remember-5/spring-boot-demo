package com.remember.demo.web.design.template;

/**
 * 抽象类定义骨架流程(查询商户信息，加签，http请求，验签)
 *
 * @author wangjiahao
 * @date 2021/11/19
 */
public abstract class CaffeineBeverage {

    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater() {
        System.err.println("Boiling water");
    }

    void pourInCup() {
        System.out.println("Pouring into Cup...");
    }


}