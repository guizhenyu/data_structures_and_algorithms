package com.gzy.code.dynamic;

/**
 * description: CoinsWayNoLimit date: 2021/12/7 1:00 下午
 *  有 n 钟
 *
 *
 *
 * @author: guizhenyu
 */
public class CoinsWayNoLimit {

  public static int coinsWay(int[] arr, int aim){
    if (arr == null || arr.length == 0 || aim < 0){
      return 0;
    }

    return process(arr, 0, aim);
  }


  /**
   * 递归处理
   * @param arr  钱币的值
   * @param index 钱币的索引值
   * @param rest 剩余多少钱
   * @return
   */
  public static  int process(int[] arr, int index, int rest){
    if(index == arr.length){
      return rest == 0? 1 : 0;
    }
    int ways = 0;
    for (int zhang = 0; zhang * arr[index] <= rest ; zhang++){
      ways += process(arr, index + 1, rest - zhang * arr[index]);
    }
    return ways;
  }

  /**
   * 动态规划改进上面递归求解的方案
   * @param arr
   * @param aim
   * @return
   */
  public static int processDp(int[] arr,  int aim){

    if(0 == arr.length || arr == null || aim < 0){
      return 0;
    }
    int N = arr.length;
    int[][] dp  = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index --){
      for (int rest = 0; rest <= aim; rest ++){
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest ; zhang++){
          ways += dp[index + 1][ rest - zhang * arr[index]];
        }
        dp[index][rest] = ways;
      }
    }
    return dp[0][aim];
  }

  public static int processDp1(int[] arr, int aim){
    if (arr == null || 0 == arr.length || aim < 0){
      return 0;
    }
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--){
      for (int rest = 0; rest <= aim; rest ++){
        dp[index][rest] = dp[index + 1][rest];
        if (rest - arr[index] >= 0){
          dp[index][rest] += dp[index][rest -arr[index]];
        }
      }
    }
    return dp[0][aim];
  }

  public static void main(String[] args) {
    int[] arr = {10, 5, 20, 50, 100};
    int rest = 100;

    System.out.println(coinsWay(arr, rest));
    System.out.println(processDp(arr, rest));
    System.out.println(processDp1(arr, rest));
  }
}
