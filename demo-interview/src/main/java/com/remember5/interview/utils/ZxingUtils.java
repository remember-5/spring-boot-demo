package com.remember5.interview.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class ZxingUtils {

    public static void main(String[] args) {
        String decode = QrCodeUtil.decode(FileUtil.file("/Users/wangjiahao/Downloads/WeChat72ae0b42bfe8681a9d0c0e0bc8407543-1.png"));
        System.err.println(decode);
    }


}
