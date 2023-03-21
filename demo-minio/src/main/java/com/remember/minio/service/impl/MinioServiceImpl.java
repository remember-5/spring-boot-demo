package com.remember.minio.service.impl;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.remember.common.entity.R;
import com.remember.common.entity.REnum;
import com.remember.minio.entity.*;
import com.remember.minio.properties.MinioProperties;
import com.remember.minio.service.MinioService;
import com.remember.minio.utils.MinioUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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


    @Override
    public SysUploadTask getByIdentifier(String identifier) {
        // todo 从redis中取
        return new SysUploadTask();
    }

    @Override
    public TaskInfoDTO initTask(InitTaskParam param) {

//        Date currentDate = new Date();
//        String bucketName = minioProperties.getDefaultBucket();
//        String fileName = param.getFileName();
//        String suffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
//        String key = StrUtil.format("{}/{}.{}", DateUtil.format(currentDate, "YYYY-MM-dd"), IdUtil.randomUUID(), suffix);
//        String contentType = MediaTypeFactory.getMediaType(key).orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        objectMetadata.setContentType(contentType);
////        InitiateMultipartUploadResult initiateMultipartUploadResult = amazonS3.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, key).withObjectMetadata(objectMetadata));
////        String uploadId = initiateMultipartUploadResult.getUploadId();
//
//        SysUploadTask task = new SysUploadTask();
//        int chunkNum = (int) Math.ceil(param.getTotalSize() * 1.0 / param.getChunkSize());
//        task.setBucketName(minioProperties.getDefaultBucket())
//                .setChunkNum(chunkNum)
//                .setChunkSize(param.getChunkSize())
//                .setTotalSize(param.getTotalSize())
//                .setFileIdentifier(param.getIdentifier())
//                .setFileName(fileName)
//                .setObjectKey(key)
//                .setUploadId(uploadId);
//        // todo 保存到redis
//        // sysUploadTaskMapper.insert(task);
//        return new TaskInfoDTO().setFinished(false).setTaskRecord(TaskRecordDTO.convertFromEntity(task)).setPath(getPath(bucketName, key));
        return null;
    }

    @Override
    public String getPath(String bucket, String objectKey) {
        return StrUtil.format("{}/{}/{}", minioProperties.getEndpoint(), bucket, objectKey);
    }

    @Override
    public TaskInfoDTO getTaskInfo(String identifier) {
//        SysUploadTask task = getByIdentifier(identifier);
//        if (task == null) {
//            return null;
//        }
//        TaskInfoDTO result = new TaskInfoDTO().setFinished(true).setTaskRecord(TaskRecordDTO.convertFromEntity(task)).setPath(getPath(task.getBucketName(), task.getObjectKey()));
//
//        boolean doesObjectExist = amazonS3.doesObjectExist(task.getBucketName(), task.getObjectKey());
//        if (!doesObjectExist) {
//            // 未上传完，返回已上传的分片
//            ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
//            PartListing partListing = amazonS3.listParts(listPartsRequest);
//            result.setFinished(false).getTaskRecord().setExitPartList(partListing.getParts());
//        }
//        return result;
        return null;
    }

    @Override
    public String genPreSignUploadUrl(String bucket, String objectKey, Map<String, String> params) {
//        Date currentDate = new Date();
//        Date expireDate = DateUtil.offsetMillisecond(currentDate, MinioConstant.PRE_SIGN_URL_EXPIRE.intValue());
//        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, objectKey)
//                .withExpiration(expireDate).withMethod(HttpMethod.PUT);
//        if (params != null) {
//            params.forEach((key, val) -> request.addRequestParameter(key, val));
//        }
//        URL preSignedUrl = amazonS3.generatePresignedUrl(request);
//        return preSignedUrl.toString();
        return null;
    }

    @Override
    public void merge(String identifier) {
//        SysUploadTask task = getByIdentifier(identifier);
//        if (task == null) {
//            throw new RuntimeException("分片任务不存");
//        }
//
//        ListPartsRequest listPartsRequest = new ListPartsRequest(task.getBucketName(), task.getObjectKey(), task.getUploadId());
//        PartListing partListing = amazonS3.listParts(listPartsRequest);
//        List<PartSummary> parts = partListing.getParts();
//        if (!task.getChunkNum().equals(parts.size())) {
//            // 已上传分块数量与记录中的数量不对应，不能合并分块
//            throw new RuntimeException("分片缺失，请重新上传");
//        }
//        CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest()
//                .withUploadId(task.getUploadId())
//                .withKey(task.getObjectKey())
//                .withBucketName(task.getBucketName())
//                .withPartETags(parts.stream().map(partSummary -> new PartETag(partSummary.getPartNumber(), partSummary.getETag())).collect(Collectors.toList()));
//        CompleteMultipartUploadResult result = amazonS3.completeMultipartUpload(completeMultipartUploadRequest);
    }


}
