package com.gzy.code.leecode.dynamic;

/**
 * description: MaxSubArraySum_53 date: 2022/11/13 15:52
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 * @author: guizhenyu
 */
public class MaxSubArraySum_53 {


  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0){
      return 0;
    }

    int len = nums.length;
    long max = Long.MAX_VALUE;
    long[][] dp = new long[len + 1][len + 1];
    for (int j = 1; j <= len; j++){
      dp[j][j - 1] = nums[j - 1];
    }

    for (int j = 2; j <= nums.length; j++){
      for (int i = j - 2; i >=0; i--){
        dp[j][i] = dp[j][j - 1] + dp[j - 1][i];

        max = Math.max(max, dp[j][i]);
      }
    }
    return (int)max;
  }

}
