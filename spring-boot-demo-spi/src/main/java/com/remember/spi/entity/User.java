package com.remember.spi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author wangjiahao
 * @date 2020/4/22
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class User {

    private String name;
    private Integer age;
}
