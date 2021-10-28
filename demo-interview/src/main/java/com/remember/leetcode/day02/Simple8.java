package com.remember.leetcode.day02;

/**
 * 移动零
 *
 * @author wangjiahao
 * @date 2021/10/28
 */
public class Simple8 {

    public static void main(String[] args) {
//        int[] nums = {0,1,0,3,12};
//        int[] nums = {0, 1, 0};
        int[] nums = {1,2,3,4,5,0,1,0,3,12,0};
        moveZeroes(nums);
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * <p>
     * 作者：力扣 (LeetCode)
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2ba4i/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        // 计算有多少个0
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[count];
                nums[count] = temp;
                count++;
            }
        }
        System.err.println(nums);

    }
}
