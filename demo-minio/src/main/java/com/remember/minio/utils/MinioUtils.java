package com.remember.minio.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.remember.minio.properties.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author wangjiahao
 * @date 2020/5/31
 */
@Slf4j
@Component
public class MinioUtils {

    @Resource
    private MinioProperties minioProperties;

    private MinioClient initClient() {
        return MinioClient.builder().endpoint(minioProperties.getEndpoint()).credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey()).build();
    }

    /**
     * 上传文件,文件夹名取日期,文件名取UUID
     *
     * @param file 文件
     * @return 保存结果
     */
    public Boolean upload(MultipartFile file, String bucket) throws IOException {
        //获取原始文件名称  XX.png   XX.png
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        //获取原始文件名称后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(StrUtil.DOT));
        //新文件名称
        String newFileName = UUID.randomUUID(true) + suffix;
        //获取当前日期作为文件夹名
        String packageName = LocalDate.now().toString();
        // 上传文件
        // packageName + "/" + fileName
        return upload(file, bucket, packageName + File.separator + newFileName);
    }

    /**
     * @param url    需要下载的文件地址
     * @param bucket 桶名称
     * @return 保存结果
     */
    public Boolean upload(String url, String bucket) {
        File file = HttpUtil.downloadFileFromUrl(url, FileUtil.getTmpDirPath());
        return upload(file, bucket);
    }

    /**
     * 有个文件类型，参考https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types
     *
     * @param file   文件
     * @param bucket 桶
     * @return 保存结果
     */
    public Boolean upload(File file, String bucket) {
        return upload(file, bucket, file.getName(), file.length());
    }

    /**
     * 上传
     * 文件夹名取日期
     * 文件名自定义
     *
     * @param fileName 文件名
     * @param file     文件
     * @return 保存结果
     */
    public Boolean upload(MultipartFile file, String bucket, String fileName) throws IOException {
        return upload(file.getInputStream(), bucket, fileName, file.getSize());
    }

    public Boolean upload(File file, String bucket, String fileName, Long fileSize) {
        return upload(FileUtil.getInputStream(file), bucket, fileName, fileSize);
    }

    /**
     * 上传文件
     *
     * @param fileInputStream inputs stream
     * @param fileName        filename
     * @param fileSize        filesize
     * @param bucket          bucket
     * @return 保存结果
     */
    public Boolean upload(InputStream fileInputStream, String bucket, String fileName, Long fileSize) {
        //文件分区名
        bucketExists(bucket);
        try {
            initClient().putObject(PutObjectArgs.builder().bucket(bucket).object(fileName).stream(fileInputStream, fileSize, StrUtil.INDEX_NOT_FOUND).contentType(FileUtil.getMimeType(fileName)).build());
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }


    /**
     * 检查文件分区是否存在，如果没有就创建
     *
     * @param bucket 文件分区名字
     */
    private void bucketExists(String bucket) {
        try {
            if (initClient().bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                return;
            }
            log.info("文件分区未存在,正在创建分区{}", bucket);
            initClient().makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("文件分区 {} 异常, {}", bucket, e.getMessage());
        }
    }


    /**
     * 获取全部bucket
     *
     * @return /
     */
    public List<Bucket> getAllBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return initClient().listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ErrorResponseException, XmlParserException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        return initClient().listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    public void removeBucket(String bucketName) throws InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        initClient().removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    public String getObjectUrl(String bucketName, String objectName, Integer expires) throws Exception {
        return initClient().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).expiry(expires).build());
    }

    /**
     * 获取文件
     *
     * @param objectName 文件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucket, String objectName) throws Exception {
        return initClient().getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        initClient().putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, stream.available(), StrUtil.INDEX_NOT_FOUND).contentType(objectName.substring(objectName.lastIndexOf("."))).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        ⼤⼩
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long size, String contextType) throws Exception {
        initClient().putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, size, StrUtil.INDEX_NOT_FOUND).contentType(contextType).build());
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return initClient().statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 删除文件
     *
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        initClient().removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).versionId("version-id").build());
        log.info("删除 {} 文件成功", objectName);
    }


}
