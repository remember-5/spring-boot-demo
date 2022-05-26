package com.remember.dynamic.datasource.jpa.config;

import cn.hutool.core.util.IdUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;

/**
 * @see "https://blog.csdn.net/u011781521/article/details/72210980"
 *
 * 自定义id生成器
 * @author wangjiahao
 * @date 2022/5/26 23:20
 */
public class MyIdGenerator extends IdentityGenerator {


    /**
     * id生成器
     * @param s The session from which the request originates
     * @param obj the entity or collection (idbag) for which the id is being generated
     *
     * @return id
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
        Object id =  IdUtil.randomUUID();
         if (id != null) {
             return (Serializable) id;
         }
        return super.generate(s, obj);
    }
}
