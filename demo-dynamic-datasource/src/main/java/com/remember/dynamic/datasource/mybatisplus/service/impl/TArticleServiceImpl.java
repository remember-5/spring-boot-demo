package com.remember.dynamic.datasource.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.dynamic.datasource.mybatisplus.entity.TArticle;
import com.remember.dynamic.datasource.mybatisplus.mapper.TArticleMapper;
import com.remember.dynamic.datasource.mybatisplus.service.TArticleService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TArticleServiceImpl extends ServiceImpl<TArticleMapper, TArticle> implements TArticleService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void useTransactionalSave() {
        this.baseMapper.insert(new TArticle("useTransactionalSave", "useTransactionalSave"));
    }


    //    ===========================分割 ===========================

    @Override
    public void unusedTransactionalBatchSave() {
        // 场景1: 两条均成功
        this.baseMapper.insert(new TArticle("a-unusedTransactionalBatchSave", "a-unusedTransactionalBatchSave"));
        this.baseMapper.insert(new TArticle("b-unusedTransactionalBatchSave", "b-unusedTransactionalBatchSave"));

        // 场景2: id重复的时候 第一条成功，第二条失败 (第一条失败的话，第二条不会执行)
        this.baseMapper.insert(new TArticle("error-unusedTransactionalBatchSave", "error-unusedTransactionalBatchSave"));
        this.baseMapper.insert(new TArticle("error-unusedTransactionalBatchSave", "error-unusedTransactionalBatchSave"));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void useTransitionalBatchSave() {
        // 场景1: 两条均成功
        this.baseMapper.insert(new TArticle("a-useTransitionalBatchSave", "a-useTransitionalBatchSave"));
        this.baseMapper.insert(new TArticle("b-useTransitionalBatchSave", "b-useTransitionalBatchSave"));

        // 场景2: 存在exception 回滚
        this.baseMapper.insert(new TArticle("error-useTransitionalBatchSave", "error-useTransitionalBatchSave"));
        this.baseMapper.insert(new TArticle("error-useTransitionalBatchSave", "error-useTransitionalBatchSave"));

    }

    //    ===========================分割 ===========================


    @Override
    public void unusedTransitionalCallInner() {
        // 场景1: 先调用方法，调用方法不回滚，如果方法异常，不会执行后续的sql
        unusedTransitionalCallInner1(); // 运行[场景2] 需要注释这行！！！]
        this.baseMapper.insert(new TArticle("unusedTransitionalCallInner", "a-unusedTransitionalCallInner"));
        // 场景2: 先调用方法，调用方法不回滚，本方法也不回滚
        unusedTransitionalCallInner1();
    }

    public void unusedTransitionalCallInner1() {
        this.baseMapper.insert(new TArticle("unusedTransitionalCallInner1", "unusedTransitionalCallInner1"));
        throw new RuntimeException("unusedTransitionalCallInner1");
    }

    //    ===========================分割 ===========================


    @Override
    public void childrenUseTransactional() {
        // 场景1: 先调用方法，调用方法回滚，如果方法异常，不会执行后续的sql
        // 如果直接调用childrenUseTransactional1(), 导致事务生效
        ((TArticleServiceImpl)AopContext.currentProxy()).childrenUseTransactional1();
        this.baseMapper.insert(new TArticle("childrenUseTransactional", "a-childrenUseTransactional"));
        // 场景2: 先调用方法，调用方法不回滚，本方法也不回滚
        ((TArticleServiceImpl)AopContext.currentProxy()).childrenUseTransactional1();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void childrenUseTransactional1() {
        this.baseMapper.insert(new TArticle("childrenUseTransactional1", "childrenUseTransactional1"));
        throw new RuntimeException("childrenUseTransactional1");
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void parentUseTransactional() {
        this.baseMapper.insert(new TArticle("parentUseTransactional", "a-parentUseTransactional"));
        // 场景1: 先调用方法，调用方法回滚，如果方法异常，不会执行后续的sql
        parentUseTransactional1(); // 运行[场景2] 需要注释这行！！！]
        this.baseMapper.insert(new TArticle("parentUseTransactional2", "b-parentUseTransactional2"));
        // 场景2: 先调用方法，调用方法回滚，本方法也回滚
        parentUseTransactional1();
    }

    @Override
    public void parentUseTransactional1() {
        this.baseMapper.insert(new TArticle("parentUseTransactional1", "parentUseTransactional1"));
//        throw new RuntimeException("parentUseTransactional1"); // 运行[场景2] 需要注释这行！！！]
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void parentUseTransactionalAll() {
        this.baseMapper.insert(new TArticle("a-parentUseTransactionalAll", "a-parentUseTransactionalAll"));
        // 场景1: 先调用方法，调用方法回滚，如果方法异常，不会执行后续的sql
//        ((TArticleServiceImpl) AopContext.currentProxy()).childernUseTransactionalAll();// 运行[场景2] 需要注释这行！！！]
        this.baseMapper.insert(new TArticle("b-parentUseTransactionalAll", "b-parentUseTransactionalAll"));
        // 场景2: 先调用方法，调用方法回滚，本方法也回滚
        ((TArticleServiceImpl) AopContext.currentProxy()).childernUseTransactionalAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void childernUseTransactionalAll() {
        this.baseMapper.insert(new TArticle("childernUseTransactionalAll", "childernUseTransactionalAll"));
//        throw new RuntimeException("childernUseTransactionalAll"); // 运行[场景2] 需要注释这行！！！]
    }
}
