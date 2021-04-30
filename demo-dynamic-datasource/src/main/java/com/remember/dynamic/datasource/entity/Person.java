package com.remember.dynamic.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_person")
public class Person implements Serializable {
    /**
     * id
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
     * create_date
     */
    @TableField(value = "create_date")
    private Date createDate;

    /**
     * update_date
     */
    @TableField(value = "update_date")
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}