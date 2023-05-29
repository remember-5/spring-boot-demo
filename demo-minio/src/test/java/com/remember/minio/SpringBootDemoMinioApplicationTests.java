package com.remember.minio;

import com.remember.minio.utils.FileSplitUtil;
import com.remember.minio.utils.MinioUtils;
import io.minio.ObjectWriteResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        List<Long> splitFile = FileSplitUtil.getSplitFile(fileUrl, count, partSize);
        for (Long fileSize:splitFile) {
            System.err.println(fileSize);
        }
//        for (int i = 0; i < count; i++) {
//            ObjectWriteResponse objectWriteResponse = minioUtils.uploadObject(bucket, fileUrl + "_" + i + ".tmp", fileUrl, contentType);
//            System.err.println(objectWriteResponse);
//        }


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

    @Test
    void testUploadSnowballObjects() throws IOException {
        String fileUrl = "/Users/fly/Downloads/nonono.mp4";
        String objectName = "nonono.mp4";
        String bucket = "package";
        int count = 3;
        long partSize = 5 * 1024 * 1024;
        // 5242880
        // 5242880
        // 3971163
        List<Long> fileSizes = new ArrayList<Long>();
        fileSizes.add(5242880L);
        fileSizes.add(5242880L);
        fileSizes.add(3971163L);
        List<String> fileNames = new ArrayList<String>();
        fileNames.add("nonono.mp4_0.tmp");
        fileNames.add("nonono.mp4_1.tmp");
        fileNames.add("nonono.mp4_2.tmp");
        List<InputStream> files = new ArrayList<InputStream>();
//        files.add(new FileInputStream(new File("/Users/fly/Downloads/nonono.mp4_0.tmp")));
        InputStream in0 = Files.newInputStream(Paths.get("/Users/fly/Downloads/nonono.mp4_0.tmp"));
        files.add(in0);
        InputStream in1 = Files.newInputStream(Paths.get("/Users/fly/Downloads/nonono.mp4_1.tmp"));
        files.add(in1);
        InputStream in2 = Files.newInputStream(Paths.get("/Users/fly/Downloads/nonono.mp4_2.tmp"));
        files.add(in2);
//        for (int i = 0; i < count; i++) {
//            String keyName = objectName + "_" + i + ".tmp";
//            fileNames.add(keyName);
//            String tmpFileUrl = fileUrl + "_" + i + ".tmp";
//            InputStream inputStream = Files.newInputStream(Paths.get(tmpFileUrl));
//            files.add(inputStream);
//        }
        ObjectWriteResponse objectWriteResponse = minioUtils.uploadSnowballObjects(bucket, fileNames, files, fileSizes);
        System.out.println(objectWriteResponse);
    }

}
