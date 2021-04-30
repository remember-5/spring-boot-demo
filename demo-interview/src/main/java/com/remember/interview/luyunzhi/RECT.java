package com.remember.interview.luyunzhi;

/**
 * @author wangjiahao
 * @date 2021/4/27
 */
public class RECT {

    private int width;
    private int height;

    public RECT() {
    }

    public RECT(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public long Area(){
        return this.height * this.width;
    }

    public static void main(String[] args) {
        RECT rect = new RECT(10,20);
        long area = rect.Area();
        System.err.println(area);
    }


}
