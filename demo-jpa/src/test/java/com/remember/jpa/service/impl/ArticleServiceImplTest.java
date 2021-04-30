package com.remember.jpa.service.impl;

import com.remember.jpa.JpaApplicationTests;
import com.remember.jpa.domain.Article;
import com.remember.jpa.repository.ArticleRepository;
import com.remember.jpa.repository.ArticleTagsRepository;
import com.remember.jpa.repository.DelRepository;
import com.remember.jpa.repository.TagsRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleServiceImplTest extends JpaApplicationTests {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    TagsRepository tagsRepository;
    @Autowired
    ArticleTagsRepository articleTagsRepository;
    @Autowired
    DelRepository delRepository;

    @Test
    public void test() {
        List<Article> all = articleRepository.findAll();

        System.err.println(articleRepository.findAll().size());
        System.err.println(tagsRepository.findAll().size());
        System.err.println(articleTagsRepository.findAll().size());
        System.err.println(delRepository.findAll().size());
    }


}