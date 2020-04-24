package com.remember.encrypt.starter.util;

/**
 * <p>字符串处理工具类</p>
 *
 * @author wangjiahao
 * @version 2020/4/23
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String string) {
        return string == null || "".equals(string) || string.length() == 0;
    }

}
