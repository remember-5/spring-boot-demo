package com.remember.dynamic.datasource.mybatisplus.mysql.service;

import com.remember.dynamic.datasource.mybatisplus.mysql.entity.LogAccess;
import com.baomidou.mybatisplus.extension.service.IService;
public interface LogAccessService extends IService<LogAccess>{

    void testBatchInsert();

}
