package com.gzy.code.basic.class21;

/**
 * description: CoinsWayNoLimit date: 2022/8/4 15:02
 *  arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * @author: guizhenyu
 */
public class CoinsWayNoLimit {


  public static void main(String[] args) {
    int maxLen = 10;
    int maxValue = 30;
    int testTime = 10000;
    System.out.println("测试开始");

    for (int i = 0; i < testTime; i++){
      int[] arr = randomArry(maxLen, maxValue);
      int aim = (int)(maxValue * Math.random()) + 1;
      int ways1 = violenceRecursive(arr, aim);
      int ways2 = dp(arr, aim);
      if (ways1 != ways2){
        System.out.println("oops");
        break;

      }
    }
    System.out.println("success");

  }

  private static int dp(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0){
      return 0;
    }
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    for (int i = N - 1; i >= 0; i--){
      for (int rest = 0; rest <= aim; rest++){
        for (int zhang = 0; zhang * arr[i] <= rest; zhang++) {
          dp[i][rest] += dp[i + 1][rest - zhang * arr[i]];
        }
      }
    }


    return dp[0][aim];
  }

  /**
   * 暴力递归
   * @param arr
   * @param aim
   * @return
   */
  private static int violenceRecursive(int[] arr, int aim) {
    if (arr == null || arr.length == 0){
      return 0;
    }

    return process(arr, 0, aim);

  }


  /**
   *  这边要抽象一下，由于这边硬币的纸张数是不限的
   *  每种货币的纸张数组成的情况是最终的答案
   *
   * @param arr
   * @param index 代表计算第几个货币的纸张数
   * @param rest
   * @return
   */
  private static int process(int[] arr, int index,int rest) {
    if (index == arr.length){
      return rest == 0? 1 : 0;
    }

    int ans = 0;
    for (int i = 0; i * arr[index] <= rest; i++){

      ans += process(arr, index + 1, rest - arr[index] * i);

    }

    return ans;
  }

  private static int[] randomArry(int maxLen, int maxValue) {

    int n = (int)(maxLen * Math.random());
    int[] arr = new int[n];
    boolean[] has = new boolean[maxValue + 1];
    for (int i = 0; i < n; i++){

      do{
        arr[i] = (int)(maxValue * Math.random());
      }while (has[arr[i]]);
      has[arr[i]] = true;
    }

    return arr;
  }

}
