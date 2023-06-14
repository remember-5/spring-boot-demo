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
package com.remember5.office.itext;

import com.remember5.office.config.PDFExportConfig;
import com.remember5.office.utils.PDFUtil;
import com.remember5.office.utils.ResourceFileUtil;
import com.remember5.office.utils.WordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2023/6/14 10:48
 */
@SpringBootTest
public class Html2PdfTest {

    @Autowired
    PDFExportConfig pdfExportConfig;

    @Test
    public void testHtml2Pdf() throws Exception {
        /**
         * 数据导出(PDF 格式)
         */
        Map<String, Object> dataMap = new HashMap<>(16);
        dataMap.put("statisticalTime", new Date().toString());
        dataMap.put("imageUrl", "http://118.25.95.207:9000/ahtc/2021-08-10/4dba6307-fde7-4c62-b825-ff921c932464.png");

        String fileName = "/Users/wangjiahao/Downloads/" + System.currentTimeMillis() + (int) (Math.random() * 90000 + 10000) + ".pdf";

        String htmlStr = PDFUtil.freemarkerRender(dataMap, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getEmployeeKpiFtl()));
//        byte[] pdfBytes = FreemarkerUtils.createPDF(htmlStr, ResourceFileUtil.getAbsolutePath(ttc));
        PDFUtil.createPDF(htmlStr, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getFontSimsun()), fileName);
//        PDFUtil.createPDF(htmlStr, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getFontSimsun()));
//        if (pdfBytes != null && pdfBytes.length > 0) {
//            final File file = FileUtil.writeBytes(pdfBytes, );
//        }
    }

    @Test
    public void testHtml2Doc() throws Exception {
        /**
         * 数据导出(PDF 格式)
         */
        Map<String, Object> dataMap = new HashMap<>(16);
        dataMap.put("statisticalTime", new Date().toString());
        dataMap.put("imageUrl", "http://118.25.95.207:9000/ahtc/2021-08-10/4dba6307-fde7-4c62-b825-ff921c932464.png");

        String pdfpath = "/Users/wangjiahao/Downloads/" + System.currentTimeMillis() + (int) (Math.random() * 90000 + 10000) + ".pdf";
        String docpath = "/Users/wangjiahao/Downloads/" + System.currentTimeMillis() + (int) (Math.random() * 90000 + 10000) + ".docx";

        String htmlStr = PDFUtil.freemarkerRender(dataMap, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getEmployeeKpiFtl()));
        PDFUtil.createPDF(htmlStr, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getFontSimsun()), pdfpath);
        PDFUtil.pdf2word(pdfpath, docpath);
//        PDFUtil.createPDF(htmlStr, ResourceFileUtil.getAbsolutePath(pdfExportConfig.getFontSimsun()));
//        if (pdfBytes != null && pdfBytes.length > 0) {
//            final File file = FileUtil.writeBytes(pdfBytes, );
//        }
    }



}
