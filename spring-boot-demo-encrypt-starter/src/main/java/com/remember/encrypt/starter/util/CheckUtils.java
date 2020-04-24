package com.remember.encrypt.starter.util;


import com.remember.encrypt.starter.exception.KeyNotConfiguredException;

/**
 * <p>辅助检测工具类</p>
 *
 * @author wangjiahao
 * @version 2020/4/23
 */
public class CheckUtils {

    public static String checkAndGetKey(String k1, String k2, String keyName) {
        if (StringUtils.isNullOrEmpty(k1) && StringUtils.isNullOrEmpty(k2)) {
            throw new KeyNotConfiguredException(String.format("%s is not configured (未配置%s)", keyName, keyName));
        }
        if (k1 == null) {
            return k2;
        }
        return k1;
    }

}
