package com.remember.interview.luyunzhi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/**
 * @author wangjiahao
 * @date 2021/4/27
 */
public class Print {
    public static void main(String[] args) {
        new Te();
    }

}

class Te extends Frame implements ActionListener {
    Color cc = Color.red;
    int x = -20, y = -50;
    Random r = new Random();

    public Te() {
        this.setLayout(null);
        Button b = new Button("画圆");
        this.add(b);
        b.setBounds(30, 30, 50, 50);
        b.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setBounds(200, 200, 500, 400);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cc = Color.red;
        this.x = r.nextInt(400);
        do {
            int x1 = r.nextInt(300);
            this.y = x1;
        } while (this.y < 50);
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(cc);
        g.drawRect(x, y, 50, 50);
        g.setColor(c);
    }
}