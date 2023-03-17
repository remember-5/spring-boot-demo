package com.remember.minio.entity;

import lombok.Data;

/**
 * 上传base64图片
 *
 * @author wangjihao
 * @date 2021/5/2
 */
@Data
public class Base64Uploader {

    /**
     * 自定义文件名
     */
    private String name;
    /**
     * base64
     */
    private String data;
}
