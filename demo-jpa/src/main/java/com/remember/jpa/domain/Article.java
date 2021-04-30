package com.remember.jpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
@Data
@Entity
@Table(name = "t_article")
public class Article implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "title")
    private String title;

    @Column(name = "intro")
    private String intro;

    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @OneToOne
    @JoinColumn(name = "is_delete", referencedColumnName = "kid")
    private Del del;


}
