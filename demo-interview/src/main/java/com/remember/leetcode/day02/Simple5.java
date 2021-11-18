package com.remember.leetcode.day02;

import java.util.HashSet;

/**
 * see https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * 只出现一次的数字
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple5 {

    public static void main(String[] args) {
        int[] nums = {4,1,2,1,2};
        int i = singleNumber(nums);
        System.err.println(i);
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     *
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     *
     * 输入: [2,2,1]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,1,2,1,2]
     * 输出: 4
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        // 移位计算 https://blog.csdn.net/xiaochunyong/article/details/7748713
//        int reduce = 0;
//        for (int num : nums) {
//            reduce =  reduce ^ num;
//        }
//        return reduce;




        HashSet<Integer> objects = new HashSet<>();
        for (int num : nums) {
            if (!objects.add(num)) {
                objects.remove(num);
            }
        }
        return (int)objects.toArray()[0];

    }
}
