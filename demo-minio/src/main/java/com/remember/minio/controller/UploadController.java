package com.remember.minio.controller;


import com.remember.common.entity.R;
import com.remember.common.entity.REnum;
import com.remember.minio.entity.Base64Uploader;
import com.remember.minio.entity.InitTaskParam;
import com.remember.minio.entity.SysUploadTask;
import com.remember.minio.entity.TaskInfoDTO;
import com.remember.minio.service.MinioService;
import io.minio.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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


    /**
     * 获取上传进度
     * @param identifier 文件md5
     * @return /
     */
    @GetMapping("/{identifier}")
    public R<TaskInfoDTO> taskInfo (@PathVariable("identifier") String identifier) {
        return R.success(minioService.getTaskInfo(identifier));
    }


    /**
     * 创建一个上传任务
     * @return
     */
    @PostMapping
    public R<TaskInfoDTO> initTask (@RequestBody InitTaskParam param, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return R.fail(REnum.A0500);
//            .error(bindingResult.getFieldError().getDefaultMessage());
        }
        return R.success(minioService.initTask(param));
    }

    /**
     * 获取每个分片的预签名上传地址
     * @param identifier
     * @param partNumber
     * @return
     */
    @GetMapping("/{identifier}/{partNumber}")
    public R preSignUploadUrl (@PathVariable("identifier") String identifier, @PathVariable("partNumber") Integer partNumber) {
        SysUploadTask task = minioService.getByIdentifier(identifier);
        if (task == null) {
            return R.fail(REnum.A0500.code, null, "分片任务不存在");
        }
        Map<String, String> params = new HashMap<>();
        params.put("partNumber", partNumber.toString());
        params.put("uploadId", task.getUploadId());
        return R.success(minioService.genPreSignUploadUrl(task.getBucketName(), task.getObjectKey(), params));
    }

    /**
     * 合并分片
     * @param identifier
     * @return
     */
    @PostMapping("/merge/{identifier}")
    public R merge (@PathVariable("identifier") String identifier) {
        minioService.merge(identifier);
        return R.success();
    }


}
