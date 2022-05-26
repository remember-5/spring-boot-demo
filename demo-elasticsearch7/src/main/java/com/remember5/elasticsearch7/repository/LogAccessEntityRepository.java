package com.remember5.elasticsearch7.repository;

import com.remember5.elasticsearch7.entity.LogAccessEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogAccessEntityRepository extends JpaRepository<LogAccessEntity, String> {
}