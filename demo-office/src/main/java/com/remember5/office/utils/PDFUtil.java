package com.remember5.office.utils;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2021/12/3
 */
public class PDFUtil {

    public PDFUtil() {
    }

    private volatile static Configuration configuration;

    static {
        if (configuration == null) {
            synchronized (PDFUtil.class) {
                if (configuration == null) {
                    configuration = new Configuration(Configuration.VERSION_2_3_28);
                }
            }
        }
    }

    /**
     * freemarker 引擎渲染 html
     *
     * @param dataMap     传入 html 模板的 Map 数据
     * @param ftlFilePath html 模板文件相对路径(相对于 resources路径,路径 + 文件名)
     *                    eg: "templates/pdf_export_demo.ftl"
     * @return
     */
    public static String freemarkerRender(Map<String, Object> dataMap, String ftlFilePath) {
        final File file = new File(ftlFilePath);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        try(Writer out = new StringWriter()) {
            configuration.setDirectoryForTemplateLoading(new File(file.getParent()));
            configuration.setLogTemplateExceptions(false);
            configuration.setWrapUncheckedExceptions(true);
            Template template = configuration.getTemplate(file.getName());
            template.process(dataMap, out);
            out.flush();
            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用 iText 生成 PDF 文档
     *
     * @param html     html 模板文件字符串
     * @param fontFile 所需字体文件(相对路径+文件名)
     */
    public static byte[] createPDF(String html, String fontFile) {
        byte[] result = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter pdfWriter = new PdfWriter(outputStream);

            DefaultFontProvider fontProvider = new DefaultFontProvider();
            fontProvider.addSystemFonts();
            fontProvider.addFont(fontFile);
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);

            HtmlConverter.convertToPdf(html, pdfWriter, converterProperties);
            result = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param html     html 模板文件字符串
     * @param fontFile 所需字体文件(相对路径+文件名)
     * @param savePath 保存路径
     * @return
     */
    public static void createPDF(String html, String fontFile, String savePath) throws IOException {
        try (PdfWriter pdfWriter = new PdfWriter(savePath)) {
            DefaultFontProvider fontProvider = new DefaultFontProvider();
            fontProvider.addFont(fontFile);
            fontProvider.addSystemFonts();

            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);

            HtmlConverter.convertToPdf(html, pdfWriter, converterProperties);
        }
    }

}
