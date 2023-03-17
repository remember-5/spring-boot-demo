package com.remember.minio.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.remember.minio.entity.MinioResponse;
import com.remember.minio.properties.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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
 */
@Slf4j
@Component
public class MinioUtils {
    private static final char FILE_SEPARATOR = '/';
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public MinioUtils(@Autowired(required = false) MinioClient minioClient, MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
        this.minioClient = minioClient;
    }

    /**
     * 创建custom bucket, 不可以创建public类型的，会出现s3:list获取问题
     *
     * @param bucketName bucketName
     * @throws Exception exception
     */
    public void createCustomBucket(String bucketName) throws Exception {
        createBucket(bucketName, generateCustomPolicy(bucketName));
    }

    /**
     * 创建private bucket
     *
     * @param bucketName bucket
     * @throws Exception exception
     */
    public void createPrivateBucket(String bucketName) throws Exception {
        createBucket(bucketName, generatePrivatePolicy());
    }

    /**
     * 创建分区
     *
     * @param bucketName bucket
     * @param config     policy
     * @throws Exception exception
     */
    public void createBucket(String bucketName, String config) throws Exception {
        final boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!b) {
            // 创建
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            // 修改权限
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(config).build());
            log.info("创建bucket {} 成功", bucketName);
        } else {
            log.warn("创建bucket {} 失败, 分区已存在", bucketName);
        }
    }


    /**
     * 更改bucket为custom
     *
     * @param bucketName bucket
     * @throws Exception exception
     */
    public void setCustomBucket(String bucketName) throws Exception {
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(generateCustomPolicy(bucketName)).build());
    }

    /**
     * 更改bucket为public
     *
     * @param bucketName bucket
     * @throws Exception exception
     */
    public void setPrivateBucket(String bucketName) throws Exception {
        minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(generatePrivatePolicy()).build());
    }


    /**
     * 上传文件,文件夹名取日期,文件名取UUID
     *
     * @param file   文件
     * @param bucket bucket
     * @return 保存结果
     */
    public MinioResponse upload(MultipartFile file, String bucket) throws IOException {
        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
        String originalSuffix = originalFilename.substring(originalFilename.lastIndexOf(StrPool.DOT));
        String newFilename = UUID.randomUUID(true) + originalSuffix;
        //获取当前日期作为文件夹名
        String packageName = LocalDate.now().toString();
        // packageName + "/" + fileName
        return upload(file, bucket, packageName + FILE_SEPARATOR + newFilename);
    }

    /**
     * 上传使用自定义文件名
     *
     * @param file     文件
     * @param bucket   bucket
     * @param filename 文件名
     * @return 保存结果
     */
    public MinioResponse upload(MultipartFile file, String bucket, String filename) throws IOException {
        return upload(file.getInputStream(), bucket, filename, file.getSize());
    }

    /**
     * @param file   文件
     * @param bucket 桶
     * @return 保存结果
     */
    public MinioResponse upload(File file, String bucket) {
        return upload(file, bucket, file.getName(), file.length());
    }

    public MinioResponse upload(File file, String bucket, String filename, Long fileSize) {
        return upload(FileUtil.getInputStream(file), bucket, filename, fileSize);
    }

    /**
     * 上传文件
     *
     * @param fileInputStream inputs stream
     * @param bucket          bucket
     * @param filename        filename
     * @param fileSize        filesize
     * @return 保存结果
     */
    public MinioResponse upload(InputStream fileInputStream, String bucket, String filename, Long fileSize) {
        final String mimeType = getMimeType(filename);
        if (CharSequenceUtil.isNotBlank(mimeType)) {
            return upload(fileInputStream, bucket, filename, mimeType, fileSize);
        }
        return upload(fileInputStream, bucket, filename, "application/octet-stream", fileSize);
    }

    /**
     * 上传文件
     *
     * @param fileInputStream inputs stream
     * @param bucket          bucket
     * @param filename        filename
     * @param contentType     文件类型，参考 <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Basics_of_HTTP/MIME_types">MIME 类型</a>
     * @param fileSize        filesize
     * @return 保存结果
     */
    public MinioResponse upload(InputStream fileInputStream, String bucket, String filename, String contentType, Long fileSize) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucket).object(filename).stream(fileInputStream, fileSize, CharSequenceUtil.INDEX_NOT_FOUND).contentType(contentType).build();
            ObjectWriteResponse o = minioClient.putObject(objectArgs);

            String urlBuilder = (CharSequenceUtil.isBlank(minioProperties.getDomain()) ? minioProperties.getEndpoint() : minioProperties.getDomain()) +
                    FILE_SEPARATOR + bucket + FILE_SEPARATOR + filename;
            return new MinioResponse(o, true, urlBuilder);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * 检查文件分区是否存在，如果没有就创建
     *
     * @param bucket 文件分区名字
     */
    private void bucketExists(String bucket) {
        try {
            if (minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                return;
            }
            log.info("文件分区未存在,正在创建分区{}", bucket);
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            log.error("文件分区 {} 异常, {}", bucket, e.getMessage());
        }
    }


    /**
     * @param url    需要下载的文件地址
     * @param bucket 桶名称
     * @return 保存结果
     */
    public MinioResponse upload(String url, String bucket) {
        File file = HttpUtil.downloadFileFromUrl(url, FileUtil.getTmpDirPath());
        return upload(file, bucket);
    }

    /**
     * 获取全部bucket
     *
     * @return /
     */
    public List<Bucket> getAllBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.listBuckets();
    }

    /**
     * 根据bucketName获取信息
     *
     * @param bucketName bucket名称
     */
    public Optional<Bucket> getBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, ErrorResponseException, XmlParserException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        return minioClient.listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据bucketName删除信息
     *
     * @param bucketName bucket名称
     */
    public void removeBucket(String bucketName) throws InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
    }

    /**
     * 获取文件外链
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    过期时间 <=7 秒
     * @return url
     */
    public String getObjectUrl(String bucketName, String objectName, Integer expires) throws Exception {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(bucketName).object(objectName).expiry(expires != null ? expires : 60).build());
    }

    /**
     * 获取文件
     *
     * @param objectName 文件名称
     * @return ⼆进制流
     */
    public InputStream getObject(String bucket, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(bucket).object(objectName).build());
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception <a href="https://docs.minio.io/cn/java-client-api-reference.html#putObject">putObject</a>
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, stream.available(), StrUtil.INDEX_NOT_FOUND).contentType(objectName.substring(objectName.lastIndexOf("."))).build());
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
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(stream, size, StrUtil.INDEX_NOT_FOUND).contentType(contextType).build());
    }

    /**
     * 获取文件信息
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public StatObjectResponse getObjectInfo(String bucketName, String objectName) throws Exception {
        return minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    /**
     * 删除文件
     *
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     */
    public void removeObject(String bucketName, String objectName) throws ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, ServerException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).versionId("version-id").build());
        log.info("删除 {} 文件成功", objectName);
    }


    private static String getMimeType(String filename) {
        String mimeType = FileUtil.getMimeType(filename);

        if (CharSequenceUtil.isBlank(mimeType)) {
            if (CharSequenceUtil.endWithIgnoreCase(filename, ".wgt")) {
                mimeType = "application/widget";
            } else {
                mimeType = "application/octet-stream";
            }
        }
        return mimeType;
    }


    /**
     * 生成custom policy
     *
     * @param bucketName bucket
     * @return policy
     */
    private String generateCustomPolicy(String bucketName) {
        String customPolicy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Resource\":[\"\"]}]}";
        JSONObject json = JSON.parseObject(customPolicy);
        String p1 = "arn:aws:s3:::" + bucketName;
        String p2 = "arn:aws:s3:::" + bucketName + "/*";
        json.getJSONArray("Statement").getJSONObject(0).getJSONArray("Resource").set(0, p1);
        json.getJSONArray("Statement").getJSONObject(1).getJSONArray("Resource").set(0, p2);
        return json.toString();
    }

    /**
     * 生成private policy
     *
     * @return policy
     */
    private String generatePrivatePolicy() {
        return "{\"Version\":\"2012-10-17\",\"Statement\":[]}";
    }

}
