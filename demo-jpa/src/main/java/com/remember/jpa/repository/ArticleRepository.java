package com.remember.jpa.repository;


import com.remember.jpa.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {


    /**
     * 根据id查询文章
     *
     * @param id /
     * @return /
     */
    List<Article> findArticleById(Integer id);

    /**
     * ids
     *
     * @param ids /
     * @return /
     */
    List<Article> findArticlesByIdIn(List<Integer> ids);


}
