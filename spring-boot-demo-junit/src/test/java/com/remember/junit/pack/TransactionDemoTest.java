package com.remember.junit.pack;

import com.remember.junit.SpringBootDemoJunitApplication;
import org.junit.Assert;
import org.junit.Test;

public class TransactionDemoTest extends SpringBootDemoJunitApplication {


    @Test
    public void test(){
        int a = 1;
        String str = "ass";
        Assert.assertEquals(a,1);
        System.err.println("aa");
    }

}