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
package com.remember5.db2doc;

import com.remember5.db2doc.entity.DBEnum;
import com.remember5.db2doc.ui.MyGUI;
import com.remember5.db2doc.utils.Db2DocUtils;

import javax.swing.*;

/**
 * @author wangjiahao
 * @date 2023/8/30 13:11
 */
public class MainClass {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("MyGUI");
        final MyGUI myGUI = new MyGUI();
        for (DBEnum dbEnum : DBEnum.values()) {
            myGUI.getDbType().addItem(dbEnum.getDatabaseName());
        }
        Db2DocUtils.GENERATE_TYPE_LIST.forEach(e -> {
            myGUI.getGenerateType().addItem(e);
        });
        jFrame.setContentPane(myGUI.getFdsa());
        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
    }
}
