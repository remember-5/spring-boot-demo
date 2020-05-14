package com.remember.interview.test;

import java.util.Random;

/**
 * @author wangjiahao
 * @date 2020/5/12
 */
public class GeneratePassword3 {
    public static String makepwd(int len){
        char charr[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890~!@#$%^&*.?".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        for(int i=0;i<len;i++){
            stringBuilder.append(charr[r.nextInt(charr.length)]);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String makepwd = makepwd(8);
        System.err.println(makepwd);
    }
}
