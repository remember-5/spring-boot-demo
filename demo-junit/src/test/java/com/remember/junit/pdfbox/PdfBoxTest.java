/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember.junit.pdfbox;

import com.remember.junit.utils.PdfBoxUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * @author wangjiahao
 * @date 2022/11/4 10:09 AM
 */
@SpringBootTest
public class PdfBoxTest {

    @Test
    void pdfJointImg() throws IOException {

        String originalPdfUrl = "https://jf.sh.189.cn/minio/gov-miniapp/testpdf.pdf";
        String jointImageUrl = "https://jf.sh.189.cn/minio/gov-miniapp/testimg.png";
        String savePath = "/Users/wangjiahao/Downloads/";
        String resultPdfPath = savePath + "result.pdf";
        int x = 100;
        int y = 140;
        int width = 63;
        int height = 90;
        int pageNum = 10;


        // 网络资源下载到路径
//        File originalPdfFile = HttpDownloader.downloadForFile(originalPdfUrl, new File(savePath), -1, null);
//        File jointImageFile = HttpDownloader.downloadForFile(jointImageUrl, new File(savePath), -1, null);
//        PdfBoxUtil.imgInPdf(originalPdfFile, jointImageFile, resultPdfPath, pageNum, x, y, width, height);
//        final String fileName = ResourceFileUtil.getAbsolutePath("static/font/simfang.ttf");
        ClassPathResource classPathResource = new ClassPathResource("static/fonts/simfang.ttf");
        File fontFile = classPathResource.getFile();
        PdfBoxUtil.textInPdf(savePath + "pdf1.pdf", "中国电信上海分公司", savePath + "result1.pdf", 0, 180, 660, fontFile, 12);
    }

}
