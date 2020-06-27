package com.remember.jpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
@Data
@Entity
@Table(name = "t_article_tags")
public class ArticleTags {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "article_id")
	private int articleId;

	@Column(name = "tags_id")
	private int tagsId;

}
