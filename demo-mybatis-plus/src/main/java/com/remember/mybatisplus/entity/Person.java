package com.remember.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.remember.mybatisplus.handler.AESEncryptHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 */
@Data
@ApiModel(value = "人")
@Accessors(chain = true)
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
    @TableField(value = "name", typeHandler = AESEncryptHandler.class)
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
