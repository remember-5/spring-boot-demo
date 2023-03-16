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
package com.remember.junit.runner;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.UUID;
import com.remember.junit.utils.PdfBoxUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author wangjiahao
 * @date 2023/3/16 14:14
 */
@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        testPdfJoinImageAndText();
    }


    private void testPdfJoinImageAndText() {
        try {

            final String path1 = "static/images/pdf1.pdf";
            final String path2 = "static/images/sign.png";
            final String path3 = "static/fonts/simfang.ttf";

            final String pdfTmpPath = FileUtil.getTmpDirPath() + UUID.fastUUID() + ".pdf";
            final String pngTmpPath = FileUtil.getTmpDirPath() + UUID.fastUUID() + ".png";
            final String ttfTmpPath = FileUtil.getTmpDirPath() + UUID.fastUUID() + ".ttf";

            IoUtil.copy(new ClassPathResource(path1).getStream(), FileUtil.getOutputStream(pdfTmpPath));
            IoUtil.copy(new ClassPathResource(path2).getStream(), FileUtil.getOutputStream(pngTmpPath));
            IoUtil.copy(new ClassPathResource(path3).getStream(), FileUtil.getOutputStream(ttfTmpPath));

            System.err.println(pdfTmpPath);
            System.err.println(pngTmpPath);
            System.err.println(ttfTmpPath);

            final String result1 = FileUtil.getTmpDirPath() + UUID.fastUUID() + ".pdf";
            final String result2 = FileUtil.getTmpDirPath() + UUID.fastUUID() + ".pdf";

            // 380 630 100 30 1  x,y,width,height,pageNum
            PdfBoxUtil.imgInPdf(pdfTmpPath, pngTmpPath, result1, 1, 380, 630, 100, 30);
            // 文字 180 660 0  x,y,pageNum
            PdfBoxUtil.textInPdf(result1, "wangjiahao", result2, 0, 180, 660, new File(ttfTmpPath), 20);

            FileUtil.del(pdfTmpPath);
            FileUtil.del(pngTmpPath);
            FileUtil.del(ttfTmpPath);
            FileUtil.del(result1);
            FileUtil.del(result2);

            System.err.println("end");

        } catch (IOException e) {
            System.err.println(e);
//            throw new RuntimeException(e);
        }
    }

}
