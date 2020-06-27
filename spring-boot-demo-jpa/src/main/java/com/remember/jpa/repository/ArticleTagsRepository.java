package com.remember.jpa.repository;


import com.remember.jpa.domain.ArticleTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
public interface ArticleTagsRepository extends JpaRepository<ArticleTags, Long>, JpaSpecificationExecutor<ArticleTags> {



}
