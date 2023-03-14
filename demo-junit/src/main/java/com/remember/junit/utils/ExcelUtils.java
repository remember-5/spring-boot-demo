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

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.File;
import java.util.List;

/**
 * excel扩展的工具类
 * @author wangjiahao
 * @date 2023/3/14 14:55
 */
public class ExcelUtils {

    /**
     * 读取excel中的文本
     *
     * @param filePath 文件路径
     * @return 文本内容
     */
    public static String readExcel2Content(String filePath) {
        final File file = new File(filePath);
        return readExcel2Content(file);
    }


    /**
     * 读取excel中的文本
     *
     * @param file file
     * @return 文本内容
     */
    public static String readExcel2Content(File file) {
        ExcelReader reader = ExcelUtil.getReader(file);
        // sheet = 0
        List<List<Object>> readAll = reader.read();

        final String sheet0Str = list2String(readAll);
        // 获取多页
        final int sheetCount = reader.getSheetCount();
        if (sheetCount > 1) {
            StringBuilder result = new StringBuilder(sheet0Str);
            for (int i = 1; i < sheetCount; i++) {
                reader.setSheet(i);
                final List<List<Object>> read = reader.read();
                result.append(list2String(read));
            }
            return result.toString();
        }
        return sheet0Str;
    }

    /**
     * 拼接数据为string类型
     *
     * @param data data
     * @return string字符串
     */
    private static String list2String(List<List<Object>> data) {
        StringBuilder sb = new StringBuilder();
        for (List<Object> datum : data) {
            for (Object obj : datum) {
                if (null != obj) {
                    sb.append(obj);
                }
            }
        }
        return sb.toString();
    }

}
