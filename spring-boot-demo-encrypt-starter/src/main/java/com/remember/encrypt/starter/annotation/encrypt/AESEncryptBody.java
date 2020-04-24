package com.remember.encrypt.starter.annotation.encrypt;

import java.lang.annotation.*;

/**
 * @author wangjiahao
 * @version 2018/9/4
 * @see EncryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AESEncryptBody {

    String otherKey() default "";

}
