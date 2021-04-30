package com.remember.jpa.service.impl;

import com.remember.jpa.domain.Article;
import com.remember.jpa.repository.ArticleRepository;
import com.remember.jpa.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    @Override
    public List<Article> findAll() {
        List<Article> all = articleRepository.findAll();
        return all;
    }
}
