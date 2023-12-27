package com.remember5.leetcode.day02;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 两个数组的交集 II
 * see https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2y0c2/
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple6 {
    public static void main(String[] args) {

        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        int[] intersect = intersect(nums1, nums2);
        for (int i : intersect) {
            System.err.println(i);
        }
    }


    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2,2]
     * 示例 2:
     *
     * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出：[4,9]
     *  
     *
     * 说明：
     *
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶：
     *
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     * 相关标签
     * 数组
     * 哈希表
     * 双指针
     * 二分查找
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : nums1) {
            map.put(i,map.getOrDefault(i,0)+1);
        }

        for (int i : nums2) {
            if (map.getOrDefault(i, 0) > 0){
                list.add(i);
                map.put(i,map.get(i)-1);
            }
        }

        int[] result = new int[list.size()];
        for (int i1 = 0; i1 < list.size(); i1++) {
            result[i1] = list.get(i1);
        }
        return result;



    }



}
