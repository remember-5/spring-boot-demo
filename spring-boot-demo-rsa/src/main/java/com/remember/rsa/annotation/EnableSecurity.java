package com.remember.rsa.annotation;

import com.remember.rsa.advice.EncryptRequestBodyAdvice;
import com.remember.rsa.advice.EncryptResponseBodyAdvice;
import com.remember.rsa.config.SecretKeyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author:Bobby
 * DateTime:2019/4/9 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class,
        EncryptResponseBodyAdvice.class,
        EncryptRequestBodyAdvice.class})
public @interface EnableSecurity{

}
