package com.remember.junit.pack;

import com.remember.junit.SpringBootDemoJunitApplication;
import org.junit.Assert;
import org.junit.Test;

public class TransactionDemoTest extends SpringBootDemoJunitApplication {


    @Test
    public void test() {
        int a = 1;
        String str = "ass";
        Assert.assertEquals(a, 1);
        System.err.println("aa");
    }


    /**
     * 调用service
     */
    @Test
    public void test1() {
        // 读文件夹
        // for遍历


        // 调service


        //


    }

    /**
     * 调用api形式
     */
    @Test
    public void test2() {
        // 读文件夹
        // for遍历

        for (int i = 0; i < 100; i++) {

        }


        // 调用api


        //


    }

}