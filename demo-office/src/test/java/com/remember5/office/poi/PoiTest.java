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

import com.remember5.office.utils.ExcelUtils;
import com.remember5.office.utils.ResourceFileUtil;
import com.remember5.office.utils.WordUtils;
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

import static com.remember5.office.utils.WordUtils.doWriter;

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

        FileInputStream fileInputStream =  new FileInputStream(ResourceFileUtil.getFile("files/test.docx"));
        WordUtils.doWriter(fileInputStream, dataMap, resultSavePath);
    }

}
