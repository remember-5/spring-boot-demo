package com.remember.encrypt.starter.annotation.encrypt;


import com.remember.encrypt.starter.enums.SHAEncryptType;

import java.lang.annotation.*;

/**
 * @author wangjiahao
 * @version 2018/9/4
 * @see EncryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SHAEncryptBody {

    SHAEncryptType value() default SHAEncryptType.SHA256;

}
