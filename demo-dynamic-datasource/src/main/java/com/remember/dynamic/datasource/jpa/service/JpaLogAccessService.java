package com.remember.dynamic.datasource.jpa.service;

/**
 * @author wangjiahao
 * @date 2022/5/26 12:30
 */
public interface JpaLogAccessService {


    /**
     * mysql的插入
     */
    void mysqlInsert();

    /**
     * postgres插入
     */
    void postgresInsert();

}
