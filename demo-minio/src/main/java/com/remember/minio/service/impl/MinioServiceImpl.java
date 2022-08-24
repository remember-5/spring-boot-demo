package com.remember.minio.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.remember.common.entity.R;
import com.remember.common.entity.REnum;
import com.remember.minio.entity.Base64DecodedMultipartFile;
import com.remember.minio.properties.MinioProperties;
import com.remember.minio.service.MinioService;
import com.remember.minio.utils.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangjiahao
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {
    private final MinioProperties minioProperties;
    private final MinioUtils minioUtils;

    /**
     * @Author: fly
     * @Description: 文件上传
     * @Date: 2020/10/15
     * @Param: [file]
     * @Return: java.lang.String
     */
    @Override
    public R uploadFile(MultipartFile file) {
        if (ObjectUtil.isNotNull(file)) {
            boolean flag = false;
            try {
                flag = minioUtils.upload(file, minioProperties.getBucket());
                return flag ? R.success(flag) : R.fail(REnum.A0500);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return R.fail(REnum.A0500);
    }

    /**
     * base64上传
     *
     * @param fileData base64
     * @return /
     */
    @Override
    public R uploadFile(String fileData) {
        return uploadFile(Base64DecodedMultipartFile.base64ToMultipart(fileData));
    }

    @Override
    public R removeObject(String objectName) {
        try {
            minioUtils.removeObject(minioProperties.getBucket(), objectName);
            return R.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(REnum.A0500);
        }
    }

    @Override
    public InputStream getObject(String objectName) {
        try {
            return minioUtils.getObject(minioProperties.getBucket(), objectName);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


}
