package com.remember.minio.service.impl;


import cn.hutool.core.util.ObjectUtil;
import com.remember.common.entity.R;
import com.remember.common.entity.REnum;
import com.remember.minio.entity.Base64DecodedMultipartFile;
import com.remember.minio.entity.Base64Uploader;
import com.remember.minio.entity.MinioResponse;
import com.remember.minio.properties.MinioProperties;
import com.remember.minio.service.MinioService;
import com.remember.minio.utils.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
     * 文件上传
     *
     * @param file file
     * @return /
     */
    @Override
    public R uploadFile(MultipartFile file) {
        if (ObjectUtil.isNotNull(file)) {
            try {
                final MinioResponse upload = minioUtils.upload(file, minioProperties.getDefaultBucket());
                return upload.stats() ? R.success(upload.stats()) : R.fail(REnum.A0500);
            } catch (IOException e) {
                log.error("文件上传异常 {}", file.getOriginalFilename());
                return R.fail(REnum.A0700);
            }
        }
        return R.fail(REnum.A0500);
    }

    /**
     * base64上传
     *
     * @param base64Uploader base64
     * @return /
     */
    @Override
    public R uploadFile(Base64Uploader base64Uploader) {
        final String[] base64Array = base64Uploader.getData().split(",");
        String dataUir;
        String data;
        if (base64Array.length > 1) {
            dataUir = base64Array[0];
            data = base64Array[1];
        } else {
            //根据你base64代表的具体文件构建
            dataUir = "data:image/jpg;base64";
            data = base64Array[0];
        }
        MultipartFile multipartFile = new Base64DecodedMultipartFile(data, dataUir);
        return uploadFile(multipartFile);
    }

    @Override
    public R removeObject(String objectName) {
        try {
            minioUtils.removeObject(minioProperties.getDefaultBucket(), objectName);
            return R.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.fail(REnum.A0500);
        }
    }

}
