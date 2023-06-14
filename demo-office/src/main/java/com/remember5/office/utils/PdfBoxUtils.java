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
package com.remember5.office.utils;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PdfBoxUtils {

    /**
     * pdf合并拼接
     *
     * @param files      文件列表
     * @param targetPath 合并到
     * @throws IOException 读取文件异常
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
     * @param savePath    保存路径 可用FileUtil.getTmpDirPath();临时路径
     * @param pageNum     拼接到页码
     * @param x           偏移定位 x
     * @param y           偏移定位 y
     * @param width       图片宽
     * @param height      图片高
     * @throws IOException 读取文件异常
     */
    public static void imgInPdf(String originalPdf, String jointImage, String savePath, int pageNum, int x, int y, int width, int height) throws IOException {
        File originalFile = new File(originalPdf);
        File jointImageFile = new File(jointImage);
        imgInPdf(originalFile, jointImageFile, savePath, pageNum, x, y, width, height);
    }

    /**
     * 拼接图片到pdf中，拼接方式是PREPEND，默认是覆盖到pdf上层。如需要更改，请修改 PDPageContentStream.AppendMode.PREPEND
     *
     * @param originalPdf pdf源文件
     * @param jointImage  需要拼接的图片
     * @param savePath    保存路径 可用FileUtil.getTmpDirPath();临时路径
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
        //Closing the PDPageContentStream object
        contents.close();
        //Saving the document
        doc.save(savePath);
        //Closing the document
        doc.close();
    }

    public static File imgInPdf(File originalPdf, File jointImage, int pageNum, int x, int y, int width, int height) throws IOException {
        String savePath = System.getProperty("java.io.tmpdir");
        File file = new File(savePath);
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
        //Closing the PDPageContentStream object
        contents.close();
        //Saving the document
        doc.save(file);
        //Closing the document
        doc.close();
        return file;
    }

    /**
     * 在pdf中添加文本
     *
     * @param originalPdf pdf路径
     * @param jointText   添加的文本
     * @param savePath    保存路径 可用FileUtil.getTmpDirPath();临时路径
     * @param pageNum     拼接到页码 页码从0开始
     * @param x           偏移定位 x
     * @param y           偏移定位 y
     * @param fontFile    font文件
     * @param fontSize    字体大小
     * @throws IOException 读取文件异常
     */
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
        //contents.setFont(PDType1Font.TIMES_ROMAN, 12);
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

    /**
     * 读取pdf中的文字
     *
     * @param filePath 文件路径
     * @return string
     * @throws IOException 读取文件异常
     */
    public static String readPdfText(String filePath) throws IOException {
        return readPdfText(new File(filePath));
    }

    /**
     * 读取pdf中的文字
     *
     * @param file file
     * @return string
     * @throws IOException 读取文件异常
     */
    public static String readPdfText(File file) throws IOException {
        PDDocument doc = PDDocument.load(file);
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(doc);
        doc.close();
        return text;
    }
}
