package com.remember.interview.test;

/**
 * @author wangjiahao
 * @date 2020/4/28
 */
public class ThreadSimple {

    public static void main(String[] args) {

        Test aa = new Test();

//        new Thread(()->{
//            test.a();
//        }).start();
//
//
//        new Thread(()->{
//            test.b();
//        }).start();
//
//
//
//        new Thread(()->{
//            Test.c();
//            System.err.println("first");
//        }).start();
//
//
//        new Thread(()->{
//            Test.c();
//            System.err.println("second");
//        }).start();





//        new Thread(()->{
//            System.out.println("程序开始处理");
//            Stopwatch stopwatch = Stopwatch.createStarted();
//            new Test().d(); // 释放锁
//            System.err.println("d() first");
//            System.out.println("程序开始处理");
//            System.out.println("程序处理结束，耗时" + stopwatch.stop()); // 程序处理结束，耗时2.002 s
//        }).start();
//
//        new Thread(()->{
//            System.out.println("程序开始处理");
//            Stopwatch stopwatch = Stopwatch.createStarted();
//            new Test().d(); // 开始执行
//            System.err.println("d() second");
//            System.out.println("程序处理结束，耗时" + stopwatch.stop()); // 程序处理结束，耗时2.002 s
//        }).start();

        String liyuchang = String.format("hello %n %s", "liyuchang");

        System.err.println(liyuchang);


    }


}


