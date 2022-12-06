package com.gzy.code.leecode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 */
public class MaxSlidingWindow_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        /**
         * 1. 简单的方式是使用堆结构来实现，时间复杂度是O(nlogn)
         * 2. 还有一种O(n)的方法，就是双端的单调队列结构
         *
         */
        // 双端单调队列
        Deque<Integer> deque = new LinkedList<>();

        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++){
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);
        }

        ans[0] = nums[deque.peekFirst()];

        for (int i = k; i < nums.length; i++){
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.addLast(i);

            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1){
                deque.pop();
            }

            ans[i - k + 1] = nums[deque.peekFirst()];
        }


        return ans;
    }
}
