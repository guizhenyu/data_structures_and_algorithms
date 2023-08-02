package com.gzy.code.leecode.offer.day1;

import java.util.HashMap;
import java.util.Map;

public class First {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            int num = target - nums[i];
            if(map.get(num) != null){
                return new int[]{map.get(num), i};
            }else{
                map.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};

        int[] ans = twoSum(nums, 9);

        System.out.println(ans);
    }
}
