package com.remember.interview.utils;

import cn.hutool.core.util.RuntimeUtil;

/**
 * @author wangjiahao
 */
public class HutoolSample {


    public static void main(String[] args) {
        String ifconfig = RuntimeUtil.execForStr("ifconfig");
        System.err.println(ifconfig);

    }
}
