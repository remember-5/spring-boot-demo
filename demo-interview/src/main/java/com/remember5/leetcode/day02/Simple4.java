package com.remember5.leetcode.day02;

import java.util.HashSet;

/**
 * 存在重复元素
 * see https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x248f5/
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple4 {

    public static void main(String[] args) {


    }

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: [1,2,3,1]
     * 输出: true
     * 示例 2:
     *
     * 输入: [1,2,3,4]
     * 输出: false
     * 示例 3:
     *
     * 输入: [1,1,1,3,3,4,3,2,4,2]
     * 输出: true
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> objects = new HashSet<>();
        for (int num : nums) {
            if (!objects.add(num)) {
                return true;
            }
        }
        return false;
    }
}
