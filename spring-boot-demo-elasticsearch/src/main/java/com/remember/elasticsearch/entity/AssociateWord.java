package com.remember.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 联想词
 * @author wangjiahao
 * @date 2020/5/30
 */
@Data
@Builder
@Document(indexName = "associate_word", type = "associate_word", shards = 1, replicas = 0)
@AllArgsConstructor
@NoArgsConstructor
public class AssociateWord {

	/**
	 * id
	 */
	@Id
	private int id;

	/**
	 * 活动名称
	 */
	@Field(type = FieldType.Text, analyzer = "ik_smart")
	private String keyword;

	/**
	 * 状态
	 */
	@Field(type = FieldType.Integer)
	private String state;

	/**
	 * 排序
	 */
	@Field(type = FieldType.Integer)
	private String sort;

	/**
	 * 创建时间
	 */
	@Field(type = FieldType.Date)
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Field(type = FieldType.Date)
	private Date updateTime;



}
