package com.remember.minio.service;

import com.remember.common.entity.R;
import com.remember.minio.entity.Base64Uploader;
import org.springframework.web.multipart.MultipartFile;

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
     * @param base64Uploader base64
     * @return /
     */
    R uploadFile(Base64Uploader base64Uploader);

    /**
     * 删除文件
     *
     * @param objectName 文件名
     * @return /
     */
    R removeObject(String objectName);
}
