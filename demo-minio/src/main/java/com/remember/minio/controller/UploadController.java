package com.remember.minio.controller;


import com.remember.common.entity.R;
import com.remember.minio.entity.Base64Uploader;
import com.remember.minio.service.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UploadController {

    private final MinioService minioService;

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
    @PostMapping("uploadBase64File")
    public R uploadBase64File(@RequestBody Base64Uploader base64Uploader) {
        return minioService.uploadFile(base64Uploader);
    }

    /**
     * getDefaultBucket
     * 删除文件
     */
    @DeleteMapping("/delete/{objectName}")
    public R delete(@PathVariable("objectName") String objectName) {
        return minioService.removeObject(objectName);
    }

    /**
     * 获取文件预览路径
     *
     * @param filename /
     * @return /
     */
    @GetMapping("/preview/{filename}")
    public R<String> getPreviewUrl(@PathVariable("filename") String filename) {
        return null;
    }


}
