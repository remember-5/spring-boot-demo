package com.remember.encrypt.annotation.encrypt;

import java.lang.annotation.*;

/**
 * @author wangjiahao
 * @version 2018/9/4
 * @see EncryptBody
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MD5EncryptBody {
}
