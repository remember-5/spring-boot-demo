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
package com.remember5.office.poi;

/**
 * @author wangjiahao
 * @date 2023/6/14 11:50
 */

import com.remember5.office.utils.ResourceFileUtil;
import com.remember5.office.utils.WordUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PoiTest {

    @Test
    public void readExcel() throws FileNotFoundException {
//        final File file = ResourceFileUtil.getFile("files/test.xlsx");
//        final String s = ExcelUtils.readExcel2Content(file);
//        System.err.println(s);
    }


    @Test
    public void readDoc() throws IOException {
        String resultSavePath = "/Users/wangjiahao/Downloads/resultTestDoc.docx";

        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("name", "chensijie");
        dataMap.put("date", new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        dataMap.put("by", "wangjiahao");

        FileInputStream fileInputStream = new FileInputStream(ResourceFileUtil.getFile("files/test.docx"));
        WordUtils.doWriter(fileInputStream, dataMap, resultSavePath);
    }

//    @Test
//    void test1() throws Exception {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        String templateFileName = "/Users/wangjiahao/Downloads/simple.xlsx";
//
//        // 方案1 根据对象填充
//        String fileName = "/Users/wangjiahao/Downloads/simpleFill" + System.currentTimeMillis() + ".xlsx";
//        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
//        FillData fillData = new FillData();
//        fillData.setName("张三");
//        fillData.setNumber(5.2);
//        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(fillData);
//
//        //加载Excel文档
////        Workbook wb = new Workbook();
////        wb.loadFromFile(fileName);
////        //调用方法保存为PDF格式
////        wb.saveToFile("/Users/wangjiahao/Downloads/a.pdf", FileFormat.PDF);
//
//
//        // Create Workbook to load Excel file
////        Workbook workbook = new Workbook(fileName);
//
//        // Create PDF options
////        PdfSaveOptions options = new PdfSaveOptions();
////        options.setCompliance(PdfCompliance.PDF_A_1_B);
////        options.setOnePagePerSheet(true);
////        // Save the document in PDF format
////        workbook.save("/Users/wangjiahao/Downloads/Excel-to-PDF.pdf", options);
//
//
////        // 方案2 根据Map填充
////        fileName = "/Users/wangjiahao/Downloads/simpleFill" + System.currentTimeMillis() + ".xlsx";
////        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
////        Map<String, Object> map = new HashMap<>();
////        map.put("name", "张三");
////        map.put("number", 5.2);
////        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
//    }
//
//
//    @Test
//    void testExcel2Json() {
//        String filePath = "/Users/wangjiahao/Downloads/64e90931-751c-4017-83d8-bc97f9a0f8a1.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        List<Map> excelList = EasyExcel.read(filePath).sheet().doReadSync();
//
//        final Object[] keys = excelList.get(0).values().toArray();
//        excelList.remove(0);
//
//        ArrayList<JSON> jsons = new ArrayList<>();
//        excelList.forEach(list -> {
//            final Object[] originalRowData = JSON.parseObject(list.toString()).values().toArray();
//            final JSONObject newRowData = new JSONObject();
//
//            for (int i = 0; i < keys.length; i++) {
//                newRowData.put(keys[i].toString(), originalRowData[i]);
//            }
//            jsons.add(newRowData);
//        });
//        System.err.println(jsons);
//    }
//
//
//    @Test
//    void testExcel2Content() throws InterruptedException {
//        String filePath = "/Users/wangjiahao/Downloads/test.xlsx";
//
//        ExcelReader reader = ExcelUtil.getReader(filePath);
//        // sheet = 0
//        List<List<Object>> readAll = reader.read();
//
//        final String sheet0Str = list2String(readAll);
//        // 获取多页
//        final int sheetCount = reader.getSheetCount();
//        if (sheetCount > 1) {
//            StringBuilder result = new StringBuilder(sheet0Str);
//            for (int i = 1; i < sheetCount; i++) {
//                reader.setSheet(i);
//                final List<List<Object>> read = reader.read();
//                result.append(list2String(read));
//            }
//            return;
//        }
//        new CountDownLatch(0).await();
//
//    }
//
//
//    private String list2String(List<List<Object>> data) {
//        StringBuilder sb = new StringBuilder();
//        for (List<Object> datum : data) {
//            for (Object obj : datum) {
//                if (null != obj) {
//                    sb.append(obj);
//                }
//            }
//        }
//        return sb.toString();
//    }

    @Test
    public void readExcel1() {
        try (FileInputStream file = new FileInputStream(new File("/Users/wangjiahao/Downloads/12.xls"))) {
            //            XSSFWorkbook workbook = new XSSFWorkbook(file);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
//            HSSFRow row = sheet.getRow(0); // 获取第一行
//            HSSFCell cell = row.getCell(0); // 获取第一个单元格

            // 格式化value
            DataFormatter dataFormatter = new DataFormatter();

            // 获取第一列的索引（从 0 开始）
            int columnIndex = 2;
            final int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum < columnIndex) {
                // 读取失败，返回
            }
            for (int i = columnIndex; i < lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell date = row.getCell(1);
                HSSFCell week = row.getCell(2);
                HSSFCell name1 = row.getCell(3);
                HSSFCell name2 = row.getCell(4);
                System.err.println(
                        date.getDateCellValue() + " " +
                        dataFormatter.formatCellValue(week) +  " " +
                        dataFormatter.formatCellValue(name1) +  " " +
                        dataFormatter.formatCellValue(name2));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
