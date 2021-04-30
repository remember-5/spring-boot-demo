package com.remember.interview.test;

/**
 * @author wangjiahao
 * @date 2020/4/23
 */
public class AliTest {


    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test7();
        test(1, 2, 3, 4, 5, 6, 7);
    }


    /**
     * float a = 0.125f; double b = 0.125d; System.out.println((a - b) == 0.0); 代码的输出结果是什么？
     */
    public static void test1() {
        float a = 0.125f;
        double b = 0.125d;
        System.out.println((a - b) == 0.0);
    }

    /**
     * double c = 0.8; double d = 0.7; double e = 0.6; 那么 c-d 与 d-e 是否相等？
     */
    public static void test2() {
        double c = 0.8;
        double d = 0.7;
        double e = 0.6;
        if (c - d == d - e) {
            System.err.println("相等");
        }
        ;

    }

    /**
     * System.out.println(1.0 / 0); 的结果是什么？
     */
    public static void test3() {
        System.out.println(1.0 / 0);
    }


    /**
     * System.out.println(0.0 / 0.0); 的结果是什么？
     */
    public static void test4() {
        System.out.println(0.0 / 0);
    }

    /**
     * 某个类有两个重载方法：void f(String s) 和 void f(Integer i)，那么 f(null) 的会调用哪个方法？
     */
    public static void test5() {
//        test5(null);
    }


    public static void test5(String s) {

    }

    public static void test5(Integer i) {

    }


    public static void test6() {
        test6(1);
    }

    public static void test6(double d) {

    }

    public static void test6(Integer i) {

    }

    public static void test7() {
        String a = null;
        switch (a) {
            case "1":
                System.err.println("1");
                break;
            default:
                System.err.println("default");
                break;
        }
    }

    public static void test8() {
        test8(1);
    }

    public static void test8(double d) {
    }

    public static void test8(Long l) {
    }


    private static void test(int... params) {
        for (int i = 0; i < params.length; i++) {
            System.err.println(params[i]);
        }
    }

}
