package com.gzy.code.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FirstMissingPositive_41 {

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     */


    public static int firstMissingPositive(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0 && map.containsKey(nums[i] - 1)){
                map.put(nums[i] - 1, false);
            }

            if(!map.containsKey(nums[i])){
                map.put(nums[i], true);
            }
        }
        map.put(-1, true);

        int ans = Integer.MAX_VALUE;
        for (Integer num : map.keySet()){
            if (map.get(num)){
              num = num < 0 ? 1 : num + 1;
              if (num < ans){
                  stack.push(ans);
                  ans = num;
              }
            }else if(ans == num){
                ans = stack.pop();
            }


        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        int i = firstMissingPositive(nums);
        System.out.println(i);
    }
}
