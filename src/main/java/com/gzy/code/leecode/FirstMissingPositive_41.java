package com.gzy.code.leecode;



public class FirstMissingPositive_41 {

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     */


    public static int firstMissingPositive(int[] nums) {
        // todo: 想办法标记数组中的数，标记完，依次遍历数组，没有被交集的数对应的下标就是我们要找的答案
        // nums的大小为n,
        // 那么最小的正整数，肯定是在 1 ~ （n + 1）当中
        // 首先把负数变成 n + 1
        // 然后遍历，将值在 1 ~ n + 1之间的代表数组中第几个数, 将代表的数变为负数（相当于做标记）
        // 最后再遍历这个数组，如果是正数，直接返回 i + 1
        // 遍历完成，还没有找到正数，就直接返回 n + 1;

        int n = nums.length;

        for (int i = 0; i < n; i++){
            if (nums[i] <= 0){
                nums[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++){
            int num = Math.abs(nums[i]);
            if (num <= n && nums[num - 1] > 0){

                nums[num - 1] = -nums[num - 1];
            }
        }

        for (int i = 0; i < n; i++){
            if (nums[i] > 0){
                return i + 1;
            }
        }

        return n + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }
}
