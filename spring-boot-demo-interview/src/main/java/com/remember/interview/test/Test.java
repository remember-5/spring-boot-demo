package com.remember.interview.test;

/**
 * @author wangjiahao
 * @date 2020/4/28
 */
public class Test {

    public synchronized void a() {
        try {
            System.err.println("a().sleep");
            Thread.sleep(3000);
            System.err.println("a().sleep over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void b() {
        synchronized (this) {
            System.err.println("b()");
        }
    }

    public synchronized static void c() {
        System.err.println("c()");
    }

    public void d() {
        synchronized (Test.class) {
            try {
                Thread.sleep(1);
                System.err.println("d()");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
