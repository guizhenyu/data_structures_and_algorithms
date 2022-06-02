package com.gzy.code.basic.class46;

/**
 * description: RemoveBoxes date: 2022/5/31 10:56
 *
 * @author: guizhenyu
 */
public class RemoveBoxes {
  /**
   * 给定一个数组，删除数组中的数，如果相邻的数有相同的，可以一起删除，
   * 删除可以获取响应的得分：删除的数的个数 ^ 2
   * 累加这些分数，当所有的数全部删除，可以获取一个分数，求最大的分数
   *
   *
   */

  public static int way0(int[] arr){
    if (arr == null || arr.length == 0){
      return 0;
    }

    // 这个题目中有些相同的数，不在一起，但是如果中间不同的数去掉的话，就可以让两个相同的数在一起了
    // 有点消消乐的感觉
    // 需要设计一种递归公式，能够把这个包含进来
    // 这边需要假设一下arr数组的左右两边都有一个数，而且肯定于arr中的数不相等
    // 递归函数中的参数k, 代表删除[l,r]中的数时，l之前有几个数跟l的数相等，并且没有删除
    return process0(arr, 0, arr.length - 1, 0);

  }

  /**
   * 递归删除 l ~ r范围上的数
   * @param arr
   * @param l
   * @param r
   * @param k 代表删除[l,r]中的数时，l之前有几个数跟l的数相等，并且没有删除
   * @return
   */
  private static int process0(int[] arr, int l, int r, int k) {

    if (l > r){
      return 0;
    }
    // 直接删除当前的数
    int ans = process0(arr, l + 1, r, 0) + (k + 1) * (k + 1);

    for (int i = l + 1; i <= r; i++){
      if (arr[l] == arr[i]){
        ans = Math.max(ans, process0(arr, l + 1, i - 1, 0) + process0(arr, i, r,  k + 1));
      }
    }
    return ans;
  }

  public static int way1(int[] boxes){
    if (boxes == null || boxes.length == 0){
      return 0;
    }
    int n = boxes.length;
    int[][][] dp = new int[n][n][n];
    return process1(boxes, 0, n - 1, 0, dp);
  }

  private static int process1(int[] boxes, int l, int r, int k, int[][][] dp) {
    if (l > r){
      return 0;
    }
    if (dp[l][r][k] != 0){
      return dp[l][r][k];
    }

    int ans = process1(boxes, l + 1, r, 0, dp) + (k + 1)*(k + 1);
    for (int i = l + 1; i <= r; i++){
      if (boxes[l] == boxes[i]){
        ans = Math.max(ans, process1(boxes, l + 1, i - 1, 0, dp) + process1(boxes, i, r, k + 1, dp));
      }
    }
    dp[l][r][k] = ans;

    return ans;
  }

  public static int ways2(int[] boxes){
    if (boxes == null || boxes.length == 0){
      return 0;
    }

    int N = boxes.length;
    int[][][] dp = new int[N][N][N];
    return process2(boxes, 0, N - 1, 0, dp);


  }

  private static int process2(int[] boxes, int l, int r, int k, int[][][] dp) {

    if (l > r){
      return 0;
    }

    if (dp[l][r][k] != 0){
      return dp[l][r][k];
    }

    int lastEqual = l;
    while (lastEqual + 1 <= r && boxes[l] == boxes[lastEqual + 1] ){
      lastEqual++;
    }

    // 位置来到 lastEqual,  l ~ (lastEqual-1) 都是跟l 相等的
    int pre = k + (lastEqual - l) ;

    int ans = process2(boxes, lastEqual + 1, r, 0, dp) + (pre + 1) * (pre + 1);

    // arr[lastEqual] != arr[lastEqual + 1], 所以可以直接从lastEqual + 2开始遍历
    for (int i = lastEqual + 2; i <= r; i++){
      if (boxes[l] ==  boxes[i] && boxes[l] != boxes[i - 1]){
        ans = Math.max(ans, process2(boxes, lastEqual + 1, i - 1, 0, dp) + process2(boxes, i, r, pre + 1, dp));
      }
    }

    dp[l][r][k] = ans;

    return ans;
  }
}
