package com.remember5.demopackage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 */
@Data
@Accessors(chain = true)
@TableName(value = "t_person")
public class Person implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * name
     */
    @TableField(value = "name")
    private String name;

    /**
     * age
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * address
     */
    @TableField(value = "address")
    private String address;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * updateTime
     */
    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
