package com.remember.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjiahao
 * @date 2020/4/29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private Integer age;

    private String address;

}
