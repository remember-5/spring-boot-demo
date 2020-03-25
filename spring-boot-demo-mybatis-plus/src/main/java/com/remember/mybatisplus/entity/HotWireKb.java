package com.remember.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangjiahao
 * @date 2020/3/25
 */
@Data
@TableName("hot_wire_kb")
public class HotWireKb {

	@TableId(type = IdType.NONE)
	private String id;

	@TableId(value = "title")
	private String title;

}
