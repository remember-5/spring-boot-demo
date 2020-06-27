package com.remember.jpa.repository;


import com.remember.jpa.domain.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wangjiahao
 * @date 2020/6/27
 */
public interface TagsRepository extends JpaRepository<Tags, Long>, JpaSpecificationExecutor<Tags> {



}
