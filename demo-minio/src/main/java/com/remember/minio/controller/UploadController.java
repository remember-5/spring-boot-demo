package com.remember.minio.controller;


import com.remember.minio.config.MinIOConfig;
import com.remember.minio.utils.MinIOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final MinIOConfig minIOConfig;
    private final MinIOUtils minIOUtils;

    @GetMapping("upload")
    public String upload(MultipartFile file) throws Exception {
        String bucket = minIOConfig.getBucket();
        minIOUtils.upload(file, bucket);
        //使用getObject获取一个文件
        // 调用statObject()来判断对象是否存在。
//		minioClient.statObject("wangjiahao", filename);
        // 获取1.png的流并保存到photo.png文件中。
        //参数为：文件夹，要获得的文件，要写入的文件
//		minioClient.getObject("wangjiahao", filename, filename);
        // 需要在backet上面加上配置 '*.*' 才可以做永久路径，否则无效
        String url = minIOConfig.getEndpoint() + File.separator
                + minIOConfig.getBucket() + File.separator + file.getOriginalFilename();
        return "success，" + url;

    }


}
