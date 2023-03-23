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
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public static final String SUCCESS = "成功";
    public static final String FILE_EXIST = "文件已存在";
    public static final String FILE_NOT_EXIST = "文件不存在";
    public static final Boolean TRUE = true;
    public static final Boolean FALSE = false;
    public static final String MEAGER_FILE_FAIL = "合并文件失败";
    public static final String MD5_CHECK_FAIL = "md5值不同.合并出错";
    public static final String MINIO_UPLOAD_FAIL = "文件上传minio失败";
    public static final String MINIO_UPLOAD_SUCCESS = "文件上传minio成功";
    public static final String GET_URL_FAIL = "获取文件预览地址出错";
    public static final String DELETE_SUCCESS = "删除成功";
    public static final String DELETE_FAIL = "删除失败";
    public static final String DELETE_BUCKET_FAIL = "删除临时桶失败";
    public static final String CREATE_BUCKET_FAIL = "创建桶失败";
    public static final String CHUNK_EXIST = "块文件已存在";
    public static final String CHUNK_NOT_EXIST = "块文件不存在";
    public static final String CHUNK_UPLOAD_SUCCESS = "块文件上传成功";
    public static final String MERGE_FAIL = "合并失败";
    public static final String MERGE_SUCCESS = "合并成功";
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
     * 获得Bucket策略
     *
     * @param bucketName 存储桶名称
     * @return Bucket策略
     */
    public String getBucketPolicy(String bucketName) throws Exception {
        return minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
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

    public ObjectWriteResponse uploadObject(String bucket, String objectName, String filename, String contentType) {
        try {
            UploadObjectArgs objectArgs = UploadObjectArgs.builder().bucket(bucket).object(objectName).filename(filename).contentType(contentType).build();
            return minioClient.uploadObject(objectArgs);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     *  合并分片文件
     * @param bucket 桶名称
     * @param objectName 文件名称
     * @param fileUrls 分片文件名称
     * @return /
     */
    public ObjectWriteResponse composeObject(String bucket, String objectName, List<String> fileUrls) {
        try {
            List<ComposeSource> sourceObjectList = new ArrayList<ComposeSource>();
            for (String url : fileUrls) {
                sourceObjectList.add(ComposeSource.builder().bucket(bucket).object(url).build());
            }
            ComposeObjectArgs build = ComposeObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .sources(sourceObjectList)
                    .build();
            return minioClient.composeObject(build);
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
     * 判断文件是否存在
     *
     * @param bucketName 存储桶名称
     * @param objectName 文件名
     * @return true：存在；false：不存在
     */
    public boolean isObjectExist(String bucketName, String objectName) {
        boolean exist = true;
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 判断文件夹是否存在
     *
     * @param bucketName 存储桶名称
     * @param objectName 文件夹名称
     * @return true：存在；false：不存在
     */
    public boolean isFolderExist(String bucketName, String objectName) {
        boolean exist = false;
        try {
            ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                    .bucket(bucketName)
                    .prefix(objectName)
                    .recursive(false)
                    .build();
            Iterable<Result<Item>> results = minioClient.listObjects(listObjectsArgs);
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && objectName.equals(item.objectName())) {
                    exist = true;
                }
            }
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 创建文件夹或目录
     *
     * @param bucketName 存储桶名称
     * @param objectName 目录路径
     */
    public ObjectWriteResponse createDir(String bucketName, String objectName) throws Exception {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                .build();
        return minioClient.putObject(putObjectArgs);
    }

    /**
     * 根据文件前缀查询文件
     *
     * @param bucketName 存储桶名称
     * @param prefix     前缀
     * @param recursive  是否使用递归查询
     * @return MinioItem列表
     */
    public List<Item> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) throws Exception {
        List<Item> list = new ArrayList<>();
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .recursive(recursive)
                .build();
        Iterable<Result<Item>> objectsIterator = minioClient.listObjects(listObjectsArgs);
        if (objectsIterator != null) {
            for (Result<Item> o : objectsIterator) {
                Item item = o.get();
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取路径下文件列表
     *
     * @param bucketName 存储桶名称
     * @param prefix     文件名称
     * @param recursive  是否递归查找，false：模拟文件夹结构查找
     * @return 二进制流
     */
    public Iterable<Result<Item>> listObjects(String bucketName, String prefix, boolean recursive) {
        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .recursive(recursive)
                .build();
        return minioClient.listObjects(listObjectsArgs);
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
     * 切片上传 https://blog.csdn.net/qq_40096897/article/details/125474078
     */
    public void putObject() {
//        minioClient.cr
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
     * 拷贝文件
     *
     * @param bucketName    存储桶名称
     * @param objectName    文件名
     * @param srcBucketName 目标存储桶
     * @param srcObjectName 目标文件名
     */
    public ObjectWriteResponse copyObject(String bucketName, String objectName, String srcBucketName, String srcObjectName) throws Exception {
        return minioClient.copyObject(CopyObjectArgs.builder()
                .source(CopySource.builder()
                        .bucket(bucketName)
                        .object(objectName).build())
                .bucket(srcBucketName)
                .object(srcObjectName).build());
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

    /**
     * 批量删除文件
     *
     * @param bucketName 存储桶名称
     * @param keys       需要删除的文件列表
     */
    public void removeObjects(String bucketName, List<String> keys) {
        keys.forEach(key -> {
            try {
                removeObject(bucketName, key);
            } catch (Exception e) {
                log.error("批量删除失败！error:{0}", e);
            }
        });
    }

    /**
     * 获取MimeType
     *
     * @param filename 文件名称
     * @return MimeType
     */
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
