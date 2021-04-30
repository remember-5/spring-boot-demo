package com.remember.jpa.repository;


import com.remember.jpa.domain.Del;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
public interface DelRepository extends JpaRepository<Del, Long>, JpaSpecificationExecutor<Del> {


}
