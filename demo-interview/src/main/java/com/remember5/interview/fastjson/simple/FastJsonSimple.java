package com.remember5.interview.fastjson.simple;

import com.alibaba.fastjson.JSONObject;
import com.remember5.interview.fastjson.entity.Person;

/**
 * @author wangjiahao
 * @date 2020/6/11
 */
public class FastJsonSimple {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("王小明");
        person.setAge(24);
        person.setAddress(null);
        String o = JSONObject.toJSON(person).toString();
        System.err.println(o);
    }
}
