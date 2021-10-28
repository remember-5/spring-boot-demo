package com.remember.leetcode.day02;

/**
 * 旋转数组
 * see https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2skh7/
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple3 {

    /**
     * 示例 1:
     *
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate(nums, k);
    }


    public static void rotate(int[] nums, int k) {
        int[] n = nums.clone();
        for (int i = 0; i < nums.length; i++) {
            nums[(i+k)%nums.length] = n[i];
        }
        System.err.println(nums);
        System.err.println(n);


    }
}
