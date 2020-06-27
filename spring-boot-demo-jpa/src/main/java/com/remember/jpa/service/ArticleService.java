package com.remember.jpa.service;


import com.remember.jpa.domain.Article;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
public interface ArticleService {

	/**
	 * /
	 * @return /
	 */
	List<Article> findAll();

}
