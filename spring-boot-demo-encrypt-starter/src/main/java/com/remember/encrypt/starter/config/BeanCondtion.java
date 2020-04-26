package com.remember.encrypt.starter.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * @author wangjiahao
 * @version 2020/4/24
 */
public class BeanCondtion implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        EncryptConfig bean = conditionContext.getBeanFactory().getBean(EncryptConfig.class);
        System.err.println(bean.isEnable());
        return bean.isEnable();
    }
}
