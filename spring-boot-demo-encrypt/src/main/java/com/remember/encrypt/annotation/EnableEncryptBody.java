package com.remember.encrypt.annotation;


import com.remember.encrypt.advice.DecryptRequestBodyAdvice;
import com.remember.encrypt.advice.EncryptResponseBodyAdvice;
import com.remember.encrypt.config.EncryptBodyConfig;
import com.remember.encrypt.config.HttpConverterConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>启动类</p>
 * <p>使用方法：在SpringBoot的Application启动类上添加此注解即可</p>
 * @author wangjiahao
 * @version 2020/4/23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptBodyConfig.class,
        HttpConverterConfig.class,
        EncryptResponseBodyAdvice.class,
        DecryptRequestBodyAdvice.class})
public @interface EnableEncryptBody {
}
