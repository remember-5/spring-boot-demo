package com.remember.dynamic.datasource.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_menu")
public class TMenu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "\"name\"")
    private String name;

    @TableField(value = "parent_id")
    private Integer parentId;

    @TableField(value = "url")
    private String url;

    @TableField(exist = false)
    private List<TMenu> children;
}
