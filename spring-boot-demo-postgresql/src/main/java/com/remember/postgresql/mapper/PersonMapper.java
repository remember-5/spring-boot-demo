package com.remember.postgresql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remember.postgresql.entity.Person;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper extends BaseMapper<Person> {
    int updateBatch(List<Person> list);

    int batchInsert(@Param("list") List<Person> list);

    int insertOrUpdate(Person record);

    int insertOrUpdateSelective(Person record);
}