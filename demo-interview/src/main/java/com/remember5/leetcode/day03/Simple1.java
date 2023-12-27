package com.remember5.leetcode.day03;

/**
 * 反转字符串
 * see https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnhbqj/
 *
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple1 {


    public static void main(String[] args) {
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
//        char[] s = {'h','a','n','n','a','H'};
        char[] s = {'a','b','c','d','e','f'};
        reverseString(s);


    }


    public static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = temp;
        }
        System.err.println(s);
    }
}
