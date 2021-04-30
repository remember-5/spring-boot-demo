package com.remember.interview.luyunzhi;

import java.util.Arrays;

public class Simple1 {

    public static final int[] A = {10,15,12,9,7};

    public static void main(String[] args) {
        int maxValue = getMaxValue();
        System.err.println("最大值为="+maxValue);
        long l = doSum();
        System.err.println("和="+l);
    }

    public static int getMaxValue(){
        Arrays.sort(A);
        return A[A.length-1];
    }

    public static long doSum(){
        long sum = 0;
        for (int i : A) {
            sum += i;
        }
        return sum;
    }
}
