package com.remember.minio;

import com.remember.minio.utils.MinioUtils;
import io.minio.ObjectWriteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringBootDemoMinioApplicationTests {

    @Autowired
    private MinioUtils minioUtils;

    @Test
    void contextLoads() {
    }

    @Test
    void testUpload() {
        String fileUrl = "/Users/fly/Downloads/nonono.mp4";
        int count = 3;
        long partSize = 5 * 1024 * 1024;
        String bucket = "edoc";
        String contentType = "video/mp4";
        List<String> urlList = new ArrayList<String>();
//        FileSplitUtil.getSplitFile(fileUrl, count, partSize);
        for (int i = 0; i < count; i++) {
            ObjectWriteResponse objectWriteResponse = minioUtils.uploadObject(bucket, fileUrl + "_" + i + ".tmp", fileUrl, contentType);
            System.err.println(objectWriteResponse);
        }


    }

    @Test
    void testComposeObject() {
        String fileName = "nonono_temp.mp4";
        String bucket = "edoc";
        List<String> urlList = new ArrayList<String>();
        urlList.add("nonono.mp4_0.tmp");
        urlList.add("nonono.mp4_1.tmp");
        urlList.add("nonono.mp4_2.tmp");
        ObjectWriteResponse objectWriteResponse = minioUtils.composeObject(bucket, fileName, urlList);
        System.err.println(objectWriteResponse);
    }

}
