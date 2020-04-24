package com.remember.encrypt.starter.annotation.decrypt;

import java.lang.annotation.*;

/**
 * @author wangjiahao
 * @version 2018/9/7
 * @see DecryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DESDecryptBody {

    String otherKey() default "";

}
