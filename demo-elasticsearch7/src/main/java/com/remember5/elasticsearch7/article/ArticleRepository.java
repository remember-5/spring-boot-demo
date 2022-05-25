package com.remember5.elasticsearch7.article;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<TArticleEntity, Integer> {
}