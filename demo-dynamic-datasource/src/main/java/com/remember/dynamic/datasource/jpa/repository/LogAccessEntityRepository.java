package com.remember.dynamic.datasource.jpa.repository;

import com.remember.dynamic.datasource.jpa.entity.LogAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAccessEntityRepository extends JpaRepository<LogAccessEntity, String> {
}