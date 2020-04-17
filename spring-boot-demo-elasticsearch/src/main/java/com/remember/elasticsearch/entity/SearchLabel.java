package com.remember.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjiahao
 * @date 2020/4/17
 */
@Data
@AllArgsConstructor
public class SearchLabel implements Serializable {
    private String title;
    private Integer count;
}
