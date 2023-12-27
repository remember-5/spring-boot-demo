package com.remember5.interview.luyunzhi;

import java.util.Scanner;

/**
 * @author wangjiahao
 * @date 2021/4/23
 */
public class Simple {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.println("请输入第一个数字");

        long s1 = scanner.nextInt();
        System.err.println("请输入第二个数字");
        long s2 = scanner.nextInt();
        long s = s1 * s2;
        System.err.println("两个数的乘积 =" + s);
    }
}
