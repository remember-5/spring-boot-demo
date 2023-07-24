package com.remember.dynamic.datasource.mybatisplus.service;

import com.remember.dynamic.datasource.mybatisplus.entity.LogAccess;
import com.baomidou.mybatisplus.extension.service.IService;
public interface MybatisPlusLogAccessService extends IService<LogAccess>{

    /**
     * mysql的插入
     */
    void mysqlInsert();

    /**
     * postgres插入
     */
    void postgresInsert();


    /**
     * sqlite insert
     */
    void sqliteInsert();

    /**
     * 测试批量插入
     */
    void testBatchInsert();
}
