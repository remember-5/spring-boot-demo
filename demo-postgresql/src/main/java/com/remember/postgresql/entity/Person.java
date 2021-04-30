package com.remember.postgresql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "person")
public class Person {
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    @TableField(value = "\"name\"")
    private String name;

    @TableField(value = "age")
    private Integer age;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_AGE = "age";
}