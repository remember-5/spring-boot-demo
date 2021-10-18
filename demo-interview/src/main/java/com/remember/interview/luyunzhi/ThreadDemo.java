package com.remember.interview.luyunzhi;

/**
 * @author wangjiahao
 * @date 2021/4/23
 */
public class ThreadDemo {
    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
    }

}

class ThreadA implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " invoked...");
        System.err.println("I am Thread A");
    }
}

class ThreadB implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " invoked...");
        System.err.println("I am Thread B");
    }
}