package com.remember.minio.controller;


import com.remember.common.entity.R;
import com.remember.minio.entity.Base64Uploader;
import com.remember.minio.properties.MinioProperties;
import com.remember.minio.service.MinioService;
import com.remember.minio.utils.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final MinioProperties minioProperties;
    private final MinioUtils minIOUtils;
    private final MinioService minioService;
    @GetMapping("upload")
    public String upload(MultipartFile file) throws Exception {
        String bucket = minioProperties.getBucket();
        minIOUtils.upload(file, bucket);
        //使用getObject获取一个文件
        // 调用statObject()来判断对象是否存在。
//		minioClient.statObject("wangjiahao", filename);
        // 获取1.png的流并保存到photo.png文件中。
        //参数为：文件夹，要获得的文件，要写入的文件
//		minioClient.getObject("wangjiahao", filename, filename);
        // 需要在backet上面加上配置 '*.*' 才可以做永久路径，否则无效
        String url = minioProperties.getEndpoint() + File.separator
                + minioProperties.getBucket() + File.separator + file.getOriginalFilename();
        return "success，" + url;
    }

    /**
     * MultipartFile 上传形式
     *
     * @param file 文件
     * @return return
     */
    @PostMapping("upload")
    public R uploadFile(@RequestParam("file") MultipartFile file) {
        return minioService.uploadFile(file);
    }

    /**
     * base64上传
     *
     * @param base64Uploader 文件
     * @return return
     */
    @PostMapping("uploadBaseFile")
    public R uploadBase64File(@RequestBody Base64Uploader base64Uploader) {
        return minioService.uploadFile(base64Uploader.getData());
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/delete/{objectName}")
    public R delete(@PathVariable("objectName") String objectName) throws Exception {
        return minioService.removeObject(objectName);
    }

    /**
     * 在浏览器预览图片
     */
    @GetMapping("/preViewPicture/{objectName}")
    public void preViewPicture(@PathVariable("objectName") String objectName, HttpServletResponse response) throws Exception {
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            InputStream stream = minioService.getObject(objectName);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }
            byte[] bytes = output.toByteArray();
            out.write(bytes);
            out.flush();
        }
    }

}
