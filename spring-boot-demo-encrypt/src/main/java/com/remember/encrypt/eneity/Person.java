package com.remember.encrypt.eneity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2020/4/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 1220016083258239580L;
    private String name;

    private int age;

    private String address;
}
