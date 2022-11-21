package com.gzy.code.leecode.sort;


import java.util.Arrays;

/**
 *
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 */
public class ThreeSumClosest {


    public int threeSumClosestViolentRecursive(int[] nums, int target) {
        return process(nums, 0, 3, target, 0);
    }

    public int process(int[] nums, int i, int rest, int target, int sum){
        if (rest == 0){
            return sum;
        }

        if (i >= nums.length){
            return Integer.MAX_VALUE;
        }

        int p1 = process(nums, i + 1, rest - 1, target, sum +  nums[i]);
        int p2 = process(nums, i + 1, rest, target, sum);
        return Math.abs(target - p1) > Math.abs(target - p2) ? p2 : p1;
    }

    public int threeSumClosestDp(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;


        if(nums[0] < 0){
            int add = Math.abs(nums[0]);

            target += add * 3;

            for (int i = 0; i < len; i++){
                nums[i] += add;
            }
        }



        int sumMax = nums[len - 1] + nums[len - 2] + nums[len - 3];

        int rest = 3;

        int[][][] dp = new int[rest + 1][len + 1][sumMax + 1];

        for (int i = 0; i <= len; i++){
            for (int j = 0; j <= sumMax; j++){

                dp[0][i][j] = j;

            }
        }

        for (int i = 1; i <= rest; i++){
            for (int s = 0; s <= sumMax; s++){
                dp[i][len][s] = Integer.MAX_VALUE;
            }
        }

        for (int i = 1; i <= rest; i++){
            for (int j = len - 1; j >= 0; j--){

                for (int s = sumMax - nums[j]; s >= 0; s--){

                    int p1 = dp[i - 1][j + 1][s + nums[j]];
                    int p2 = dp[i][j + 1][s];
                    dp[i][j][s] = Math.abs(target - p1) > Math.abs(target - p2) ? p2 : p1;
                }
            }
        }


        return dp[3][0][0];

    }


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int first = 0; first < nums.length - 2; first++){
            int l = first + 1;
            int r = nums.length - 1;
            while (l < r){
                int sum = nums[first] + nums[l] + nums[r];

                if (Math.abs(sum - target) < Math.abs(ans - target)){
                    ans = sum;
                }

                if (sum > target){
                    r--;
                }else if(sum < target){
                    l++;
                }else {
                    return sum;
                }
            }


        }

        return ans;
    }



}
