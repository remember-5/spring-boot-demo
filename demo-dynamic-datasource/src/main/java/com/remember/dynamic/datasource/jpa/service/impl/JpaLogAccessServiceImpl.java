package com.remember.dynamic.datasource.jpa.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.remember.dynamic.datasource.jpa.entity.LogAccessEntity;
import com.remember.dynamic.datasource.jpa.repository.LogAccessEntityRepository;
import com.remember.dynamic.datasource.jpa.service.JpaLogAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wangjiahao
 * @date 2022/5/26 12:33
 */
@Service
@RequiredArgsConstructor
public class JpaLogAccessServiceImpl implements JpaLogAccessService {

    private final LogAccessEntityRepository logAccessRepository;

    @DS("mysql")
    @Override
    public void mysqlInsert() {
        LogAccessEntity logAccess = LogAccessEntity.builder()
//                .id(UUID.randomUUID().toString())
                .vMethod("jpa-mysql-test")
                .build();
        logAccessRepository.save(logAccess);
    }

    @DS("pg")
    @Override
    public void postgresInsert() {
        LogAccessEntity logAccess = LogAccessEntity.builder()
                .vMethod("jpa-pg-test")
                .build();
        logAccessRepository.save(logAccess);

    }
}
