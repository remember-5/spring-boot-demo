package com.remember.interview.luyunzhi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2021/4/22
 */
@Data
public class Person implements Serializable {

    private String name;
    private int age;
    private String address;
    private List<Person> list;
}
