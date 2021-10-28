package com.remember.leetcode.day01;

/**
 * @see 'https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2gy9m'
 * 删除排序数组中的重复项
 * @author wangjiahao
 * @date 2021/10/27
 */
public class Simple1 {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int i = removeDuplicates(nums);
        System.err.println(i);
    }


    public static int removeDuplicates(int[] nums) {
        if (nums ==null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if(nums[left] != nums[right]){
                nums[++left] = nums[right];
            }
        }
        System.err.println(nums);
        return ++left;
    }

}
