package com.remember.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.remember.mybatisplus.handler.AESEncryptHandler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjiahao
 */
@Data
@Schema(description = "人", title = "人")
@Accessors(chain = true)
@TableName(value = "t_person")
public class Person implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Integer id;

    /**
     * name
     */
    @TableField(value = "name", typeHandler = AESEncryptHandler.class)
    @Schema(description = "name")
    private String name;

    /**
     * age
     */
    @TableField(value = "age")
    @Schema(description = "age")
    private Integer age;

    /**
     * address
     */
    @TableField(value = "address")
    @Schema(description = "address")
    private String address;

    /**
     * createTime
     */
    @TableField(value = "create_time")
    @Schema(description = "createTime")
    private Date createTime;

    /**
     * updateTime
     */
    @TableField(value = "update_time")
    @Schema(description = "updateTime")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
