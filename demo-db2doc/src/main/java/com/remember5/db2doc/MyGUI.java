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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * @author wangjiahao
 * @date 2023/8/29 16:48
 */
public class MyGUI {
    private JPanel fdsa;
    private JTextField ip;
    private JTextField port;
    private JTextField username;
    private JTextField password;
    private JTextField database;
    private JComboBox generateType;
    private JComboBox dbType;
    private JButton generateButton;


    public MyGUI() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(ip.getText());
                System.err.println(port.getText());
                System.err.println(username.getText());


                JFileChooser fileChooser = new JFileChooser();
                // 打开文件夹选择对话框
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // Open the save dialog
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // 用户选择了文件夹
                    // 获取用户选择的文件夹路径
                    File selectedDir = fileChooser.getSelectedFile();
                    String dirPath = selectedDir.getAbsolutePath();
                    System.out.println("选择的文件夹路径：" + dirPath);
                    Db2DocUtils.generatedDoc();
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("MyGUI");
        final MyGUI myGUI = new MyGUI();
        Db2DocUtils.map.forEach((k,v)->{
            myGUI.dbType.addItem(k);
        });

        Db2DocUtils.list.forEach(e -> {
            myGUI.generateType.addItem(e);
        });

        jFrame.setContentPane(myGUI.fdsa);
        jFrame.setVisible(true);
//        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(800, 800);

    }
}
