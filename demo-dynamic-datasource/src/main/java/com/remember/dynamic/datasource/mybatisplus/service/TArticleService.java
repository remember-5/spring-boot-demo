package com.remember.dynamic.datasource.mybatisplus.service;

import com.remember.dynamic.datasource.mybatisplus.entity.TArticle;
import com.baomidou.mybatisplus.extension.service.IService;
public interface TArticleService extends IService<TArticle>{

    /**
     * 有事务的单条sql
     */
    void useTransactionalSave();

    //    ===========================分割 ===========================

    /**
     * 无事务的多条sql
     */
    void unusedTransactionalBatchSave();

    //    ===========================分割 ===========================

    /**
     * 有事务的多条sql
     */
    void useTransitionalBatchSave();

    /**
     * 调用内部方法，均无事务
     */
    void unusedTransitionalCallInner();

    void childrenUseTransactional();

    void childrenUseTransactional1();

    /**
     * 调用内部方法，父方法有事务
     */
    void parentUseTransactional();

    void parentUseTransactional1();

    /**
     * 调用内部方法，父子方法有事务，父调子需要加((TArticleServiceImpl) AopContext.currentProxy()) 来保证是代理调用，否则事务失效
     */
    void parentUseTransactionalAll();
    void childernUseTransactionalAll();

}
