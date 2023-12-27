package com.remember5.interview.luyunzhi;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author wangjiahao
 * @date 2021/4/23
 */
public class MyTest {

    public static void main(String[] args) {
        int i = 0;
        String[] choise = {"A", "B", "C"};
        Scanner cin = new Scanner(System.in);
        try {
            System.out.println("----Flag 1 ----");
            System.out.println("请输入你的选项（1，2，3）");
            i = cin.nextInt();
            System.out.println(choise[i - 1]);
            System.out.println("----Flag 2 ----");
        } catch (InputMismatchException e) {
            System.out.println("----Flag 3 ----");
            System.out.println("输入数据格式错，要求是整型！");
            System.out.println("----Flag 4 ----");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("----Flag 3 ----");
            System.out.println("数组越界！");
            System.out.println("----Flag 4 ----");
        } finally {
            System.out.println("----Flag 5 ----");
            System.out.println("执行完毕！");
        }
        System.out.println("----Flag 6 ----");
    }

}
