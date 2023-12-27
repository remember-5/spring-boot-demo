package com.remember5.demopackage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remember5.demopackage.entity.Person;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author wangjiahao
 */
@Mapper
public interface PersonMapper extends BaseMapper<Person> {
}
