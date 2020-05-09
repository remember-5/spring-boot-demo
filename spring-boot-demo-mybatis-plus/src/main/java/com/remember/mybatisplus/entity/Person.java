package com.remember.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
/**
 * @author wangjiahao
 */
@Data
@ApiModel(value = "人")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_person")
public class Person implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Integer id;

    /**
     * name
     */
    @TableField(value = "name")
    @ApiModelProperty(value = "name")
    private String name;

    /**
     * age
     */
    @TableField(value = "age")
    @ApiModelProperty(value = "age")
    private Integer age;

    /**
     * address
     */
    @TableField(value = "address")
    @ApiModelProperty(value = "address")
    private String address;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "createTime")
    private Date createTime;

    /**
     * updateTime
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "updateTime")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}