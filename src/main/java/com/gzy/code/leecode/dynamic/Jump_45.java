package com.gzy.code.leecode.dynamic;

/**
 * description: Jump_45 date: 2022/11/13 08:26
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 假设你总是可以到达数组的最后一个位置。
 * @author: guizhenyu
 */
public class Jump_45 {


  public static int jump(int[] nums) {
    int ans = process(nums, 1, nums[0] - 1);
    return  ans == Integer.MAX_VALUE ? Integer.MAX_VALUE : ans + 1;
  }
  private static int process(int[] nums, int i, int limit) {

    if (i >= nums.length - 1){
      return 0;
    }
    if (nums[i] == 0 && limit == 0){
      return Integer.MAX_VALUE;
    }
    int ans = Integer.MAX_VALUE;
    // 不换limit
    if (limit > 0){
      ans = process(nums, i + 1, limit - 1);
    }
    // 换limit
    if(nums[i] > 0){
      int p = process(nums, i + 1, nums[i] - 1);
      if (p != Integer.MAX_VALUE){
        ans = Math.min(ans, p + 1);
      }

    }
    return ans;
  }
  public static int jumpDp(int[] nums) {
    if(nums == null || nums.length <= 1){
      return 0;
    }
    int len = nums.length;
    int maxValue = 0;
    for (int i = 0; i < len; i++){
      maxValue = Math.max(maxValue, nums[i]);
    }

    int[][] dp = new int[len][maxValue + 1];
    for (int i = 0; i < len - 1; i++){
      for (int j = 0; j <= maxValue; j++){
        dp[i][j] = Integer.MAX_VALUE;
      }
    }

    for (int j = 0; j <= maxValue; j++){
      dp[len - 1][j] = 0;
    }

    for (int i = len - 2; i >= 0; i--){
      int ans = Integer.MAX_VALUE;
      if (nums[i] > 0){
        int p = dp[i + 1][nums[i] - 1];
        if (p != Integer.MAX_VALUE){
          ans = Math.min(ans, p + 1);
        }
      }
      for (int j = 1; j <= maxValue; j++){
        ans = Math.min(ans, dp[i + 1][j - 1]);
        dp[i][j] = ans;
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 1; i <= nums[0]; i++){
      ans = Math.min(ans, dp[0][i]);
    }
    return ans;
  }


  public static int jump1(int[] nums) {
    int length = nums.length;
    int end = 0;
    int maxPosition = 0;
    int steps = 0;
    for (int i = 0; i < length - 1; i++) {
      maxPosition = Math.max(maxPosition, i + nums[i]);
      if (i == end) {
        end = maxPosition;
        steps++;
      }
    }
    return steps;
  }


  public static void main(String[] args) {
//    int[] nums = new int[]{5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5};
    int[] nums = new int[]{2,3,1,1,4};

    System.out.println(jumpDp(nums));
    System.out.println(jump1(nums));

    System.out.println(Integer.MAX_VALUE);
  }

}
