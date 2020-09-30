package com.remember.jpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
@Data
@Entity
@Table(name = "t_tags")
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


}
