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
package com.remember.junit.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 合并PDF及PDF转图片
 * <a href="https://blog.csdn.net/qinwenjng120/article/details/105395179">pdf基本操作</a>
 *
 * @author wangjiahao
 * @date 2022/11/3 11:00 AM
 */
public class PdfBoxUtil {

    /**
     * pdf合并拼接
     *
     * @param files      文件列表
     * @param targetPath 合并到
     * @throws IOException /
     */
    public static File somePdfToOne(List<File> files, String targetPath) throws IOException {
        // pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        for (File f : files) {
            if (f.exists() && f.isFile()) {
                // 循环添加要合并的pdf
                mergePdf.addSource(f);
            }
        }
        // 设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(targetPath);
        // 合并pdf
        mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        return new File(targetPath);
    }

    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param filePath pdf完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     */
    public static void pdf2Image(String filePath, String dstImgFolder, int dpi) {
        File file = new File(filePath);
        PDDocument pdDocument;
        try {
            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder + File.separator + imagePDFName;
            }
            if (createDirectory(imgFolderPath)) {
                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
                /* dpi越大转换后越清晰，相对转换速度越慢 */
                int pages = pdDocument.getNumberOfPages();
                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, "png", dstFile);
                }
                System.out.println("PDF文档转PNG图片成功！");
            } else {
                System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

    /**
     * 拼接图片到pdf中，拼接方式是PREPEND，默认是覆盖到pdf上层。如需要更改，请修改 PDPageContentStream.AppendMode.PREPEND
     *
     * @param originalPdf pdf源文件路径
     * @param jointImage  需要拼接的图片路径
     * @param savePath    保持路径
     * @param pageNum     拼接到页码
     * @param x           偏移定位 x
     * @param y           偏移定位 y
     * @param width       图片宽
     * @param height      图片高
     * @throws IOException 读取文件异常
     */
    public static void imgInPdf(String originalPdf, String jointImage, String savePath, int pageNum, int x, int y, int width, int height) throws IOException {
        //Loading an existing document
        File originalFile = new File(originalPdf);
        File jointImageFile = new File(jointImage);
        imgInPdf(originalFile, jointImageFile, savePath, pageNum, x, y, width, height);
    }

    /**
     * 拼接图片到pdf中，拼接方式是PREPEND，默认是覆盖到pdf上层。如需要更改，请修改 PDPageContentStream.AppendMode.PREPEND
     *
     * @param originalPdf pdf源文件
     * @param jointImage  需要拼接的图片
     * @param savePath    保持路径
     * @param pageNum     拼接到页码 页码从0开始
     * @param x           偏移定位 x
     * @param y           偏移定位 y
     * @param width       图片宽
     * @param height      图片高
     * @throws IOException 读取文件异常
     */
    public static void imgInPdf(File originalPdf, File jointImage, String savePath, int pageNum, int x, int y, int width, int height) throws IOException {
        //Loading an existing document
        PDDocument doc = PDDocument.load(originalPdf);
        //Retrieving the page
        PDPage page = doc.getPage(pageNum);
        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(jointImage, doc);
        //creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.PREPEND, true, false);
        //Drawing the image in the PDF document
        contents.drawImage(pdImage, x, y, width, height);
        System.out.println("Image inserted");
        //Closing the PDPageContentStream object
        contents.close();
        //Saving the document
        doc.save(savePath);
        //Closing the document
        doc.close();
    }


    public static void textInPdf(String originalPdf, String jointText, String savePath, int pageNum, int x, int y, File fontFile, float fontSize) throws IOException {
        File originalFile = new File(originalPdf);
        // Loading an existing document
        PDDocument doc = PDDocument.load(originalFile);
        // Retrieving the page
        PDPage page = doc.getPage(pageNum);
        // creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.PREPEND, true, false);

        PDType0Font font1 = PDType0Font.load(doc, fontFile);
        contents.beginText();
//        contents.setFont(PDType1Font.TIMES_ROMAN, 12);
        contents.setFont(font1, fontSize);
        contents.newLineAtOffset(x, y);
        contents.showText(jointText);
        contents.endText();

        //Closing the PDPageContentStream object
        contents.close();
        //Saving the document
        doc.save(savePath);
        //Closing the document
        doc.close();
    }

    public static String readPdfText(String filePath) throws IOException {
        final File file = new File(filePath);
        PDDocument doc = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(doc);
        doc.close();
        return text;
    }


    public static void main(String[] args) throws IOException {
        TimeInterval timer = DateUtil.timer();
//        String originalPdfUrl = "https://jf.sh.189.cn/minio/gov-miniapp/testpdf.pdf";
//        String jointImageUrl = "https://jf.sh.189.cn/minio/gov-miniapp/testimg.png";
        String savePath = "/Users/wangjiahao/Downloads/";
//        String resultPdfPath = savePath + "result.pdf";
//        int x = 100;
//        int y = 150;
//        int width = 63;
//        int height = 90;
//        int pageNum = 10;

        // 网络资源下载到路径
//        File originalPdfFile = HttpDownloader.downloadForFile(originalPdfUrl, new File(savePath), -1, null);
//        File jointImageFile = HttpDownloader.downloadForFile(jointImageUrl, new File(savePath), -1, null);
//        imgInPdf(originalPdfFile, jointImageFile, resultPdfPath, 10, x, y, width, height);
//        imgInPdf(savePath + "pdf1.pdf", savePath + "img.png", savePath + "result1.pdf", 4, 220, 255, 100, 30);
//        imgInPdf(savePath + "pdf2.pdf", savePath + "img.png", savePath + "result2.pdf", 0, 200, 390, 100, 30);
//        imgInPdf(savePath + "pdf3.pdf", savePath + "img.png", savePath + "result3.pdf", 10, 150, 163, 100, 30);
//        imgInPdf(savePath + "pdf4.pdf", savePath + "img.png", savePath + "result4.pdf", 0, 140, 250, 100, 30);
        File fontFile = new File("/Users/wangjiahao/IdeaProjects/spring-boot-demo/demo-junit/src/main/java/com/remember/junit/utils/simfang.ttf");
        textInPdf(savePath + "pdf1.pdf", "中国电信上海分公司", savePath + "result1.pdf", 0, 180, 660, fontFile, 12);
        System.err.println(readPdfText(savePath + "pdf1.pdf"));
        System.err.println(timer.interval());
    }


}
