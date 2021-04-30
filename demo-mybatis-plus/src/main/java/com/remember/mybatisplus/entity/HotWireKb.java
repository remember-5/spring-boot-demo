package com.remember.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangjiahao
 * @date 2020/3/25
 */
@Data
@TableName("hot_wire_kb")
@AllArgsConstructor
@NoArgsConstructor
public class HotWireKb {

    /**
     *
     */
    @TableId(type = IdType.NONE)
    private int id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 数据源
     */
//    @TableField(value = "origin")
//    private String origin;

    /**
     * 等级
     */
    @TableField(value = "top_level")
    private Integer topLevel;

    /**
     * 状态
     */

    @TableField(value = "status")
    private Integer status;


    /**
     * 绑定一级标签
     */

    @TableField(value = "attrs_secondTag")
    private String attrsSecondTag;

    /**
     * 绑定二级标签
     */

    @TableField(value = "attrs_firstTag")
    private String attrsFirstTag;

    /**
     * 绑定渠道名称
     */

    @TableField(value = "attrs_channelName")
    private String attrsChannelName;

    /**
     * 关键词
     */

    @TableField(value = "attrs_keyword")
    private String attrsKeyword;

    /**
     * 渠道id
     */

    @TableField(value = "channel_id")
    private Integer channelId;

    /**
     * 渠道名称
     */

    @TableField(value = "channel_name")
    private String channelName;

    /**
     * 类型id
     */

    @TableField(value = "type_id")
    private Integer typeId;

    /**
     * 类型名称
     */

    @TableField(value = "type_name")
    private String typeName;

    /**
     * 模板id
     */

    @TableField(value = "model_id")
    private Integer modelId;

    /**
     * 模板名称
     */

    @TableField(value = "model_name")
    private String modelName;

    /**
     * 状态
     */

    @TableField(value = "synth_flag")
    private Integer synthFlag;

    /**
     * 状态
     */

    @TableField(value = "masterFlag")
    private Integer masterFlag;

    /**
     * 发布时间
     */

    @TableField(value = "release_date")
    private Date releaseDate;

    /**
     * 创建时间
     */

    @TableField(value = "create_date")
    private Date createDate;

    /**
     * 更新时间
     */

    @TableField(value = "update_date")
    private Date updateDate;

}
