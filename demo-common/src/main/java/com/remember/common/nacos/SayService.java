package com.remember.common.nacos;

/**
 * @author wangjiahao
 * @date 2021/10/22
 */
public interface SayService {

    /**
     * 根据名字say hello
     * @param name 名字
     * @return name + hello
     */
    String sayHelloByName(String name);
}
