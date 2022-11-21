package com.gzy.code.leecode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {

    /**
     *
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
     * 满足 i != j、i != k 且 j != k ，
     * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组。
     *
     *    3 <= nums.length <= 3000
     *    -105 <= nums[i] <= 105
     */



    /**
     * 思路：
     * 1. 先排序，方便后面的寻找
     * 2. 然后采用双指针，来寻找另外两个符合要求的数
     *    2.1 如果第一个数 > 0, 由于后面的数都比第一个数大，所以三个数加起来肯定 > 0,就没有遍历的必要，直接退出
     *        如果， 第一个 开始切换到后面的数时，如果后面的数跟他一样，为了避免重复，也要跳出当前情况的遍历，开始下一个数的情况遍历
     *
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int first = 0;
        int len = nums.length;
        while (first <= len - 2){
            if (nums[first] > 0){
                break;
            }
            // 去重复的
            if (first > 0 && nums[first] == nums[first - 1]){
                first++;
                continue;
            }
            int l = first + 1;
            int r = len - 1;
            while (l < r){
                if (nums[first] + nums[l] + nums[r] == 0){
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[first]);
                    result.add(nums[l]);
                    result.add(nums[r]);
                    ans.add(result);

                    while (l < r && nums[l] == nums[l + 1]){
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]){
                        r--;
                    }
                    l++;
                    r--;
                }else if(nums[first] + nums[l] + nums[r] < 0){
                    l++;
                }else {
                    r--;
                }
            }

            first++;
        }
        return ans;
    }
}
