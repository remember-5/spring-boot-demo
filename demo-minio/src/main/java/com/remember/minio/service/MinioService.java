package com.remember.minio.service;

import com.remember.common.entity.R;
import com.remember.minio.entity.Base64Uploader;
import com.remember.minio.entity.InitTaskParam;
import com.remember.minio.entity.SysUploadTask;
import com.remember.minio.entity.TaskInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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


    /**
     * 根据md5标识获取分片上传任务
     * @param identifier
     * @return
     */
    SysUploadTask getByIdentifier (String identifier);

    /**
     * 初始化一个任务
     * @param param
     * @return
     */
    TaskInfoDTO initTask (InitTaskParam param);

    /**
     * 获取文件地址
     * @param bucket
     * @param objectKey
     * @return
     */
    String getPath (String bucket, String objectKey);

    /**
     * 获取上传进度
     * @param identifier
     * @return
     */
    TaskInfoDTO getTaskInfo (String identifier);

    /**
     * 生成预签名上传url
     * @param bucket 桶名
     * @param objectKey 对象的key
     * @param params 额外的参数
     * @return
     */
    String genPreSignUploadUrl (String bucket, String objectKey, Map<String, String> params);

    /**
     * 合并分片
     * @param identifier
     */
    void merge (String identifier);
}
