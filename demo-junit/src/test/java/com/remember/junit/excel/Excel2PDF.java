package com.remember.junit.excel;


import com.alibaba.excel.EasyExcel;
import com.remember.junit.entity.FillData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangjiahao
 * @date 2021/12/3
 */
@SpringBootTest
public class Excel2PDF {

    @Test
    void test1() throws Exception {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "/Users/wangjiahao/Downloads/simple.xlsx";

        // 方案1 根据对象填充
        String fileName = "/Users/wangjiahao/Downloads/simpleFill" + System.currentTimeMillis() + ".xlsx";
        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        FillData fillData = new FillData();
        fillData.setName("张三");
        fillData.setNumber(5.2);
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);

        //加载Excel文档
//        Workbook wb = new Workbook();
//        wb.loadFromFile(fileName);
//        //调用方法保存为PDF格式
//        wb.saveToFile("/Users/wangjiahao/Downloads/a.pdf", FileFormat.PDF);




        // Create Workbook to load Excel file
//        Workbook workbook = new Workbook(fileName);

        // Create PDF options
//        PdfSaveOptions options = new PdfSaveOptions();
//        options.setCompliance(PdfCompliance.PDF_A_1_B);
//        options.setOnePagePerSheet(true);
//        // Save the document in PDF format
//        workbook.save("/Users/wangjiahao/Downloads/Excel-to-PDF.pdf", options);


//        // 方案2 根据Map填充
//        fileName = "/Users/wangjiahao/Downloads/simpleFill" + System.currentTimeMillis() + ".xlsx";
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "张三");
//        map.put("number", 5.2);
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
    }

}
