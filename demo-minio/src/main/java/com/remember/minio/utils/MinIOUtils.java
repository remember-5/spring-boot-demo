package com.remember.minio.utils;


import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Slf4j
@Component
public class MinIOUtils {

    @Resource
    private MinioClient minioClient;

    public void upload(MultipartFile file, String bucket) throws Exception {
        bucketExists(bucket);
        // 使用putObject上传一个文件到文件夹中。
        PutObjectOptions putObjectOptions = new PutObjectOptions(file.getSize(), -1);
        putObjectOptions.setContentType(Objects.requireNonNull(file.getContentType()));
        minioClient.putObject(bucket, Objects.requireNonNull(file.getOriginalFilename()), file.getInputStream(), putObjectOptions);

    }

    /**
     * 检查文件夹是否已经存在
     *
     * @param bucket 区域名
     * @throws Exception 创建异常
     */
    private void bucketExists(String bucket) throws Exception {
        // 检查文件夹是否已经存在
        boolean isExist = minioClient.bucketExists(bucket);
        if (isExist) {
            log.info("文件夹已经存在了");
        } else {
            // 创建一个名为managertest的文件夹
            log.info("文件夹还没存在");
            minioClient.makeBucket(bucket);
        }
    }


}
