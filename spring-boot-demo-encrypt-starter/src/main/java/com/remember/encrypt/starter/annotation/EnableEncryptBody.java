package com.remember.encrypt.starter.annotation;


import com.remember.encrypt.starter.advice.DecryptRequestBodyAdvice;
import com.remember.encrypt.starter.advice.EncryptResponseBodyAdvice;
import com.remember.encrypt.starter.config.EncryptConfig;
import com.remember.encrypt.starter.config.HttpConverterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>启动类(已弃用)</p>
 * <p>使用方法：在SpringBoot的Application启动类上添加此注解即可</p>
 * @author wangjiahao
 * @version 2020/4/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
@Deprecated
public @interface EnableEncryptBody {
}
