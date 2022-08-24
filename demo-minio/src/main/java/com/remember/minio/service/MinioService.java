package com.remember.minio.service;

import com.remember.common.entity.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author wangjiahao
 */
public interface MinioService {

    /**
     * 文件上传
     *
     * @param file 文件
     * @return /
     */
    R uploadFile(MultipartFile file);

    /**
     * base64上传
     *
     * @param fileData base64
     * @return /
     */
    R uploadFile(String fileData);

    /**
     * 删除文件
     *
     * @param objectName 文件名
     * @return /
     */
    R removeObject(String objectName);

    /**
     * 下载文件流
     *
     * @param objectName 文件名
     * @return /
     */
    InputStream getObject(String objectName);
}
