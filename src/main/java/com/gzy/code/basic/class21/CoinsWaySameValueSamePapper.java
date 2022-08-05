package com.gzy.code.basic.class21;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * description: CoinsWaySameValueSamePapper date: 2022/8/5 14:08
 *
 * @author: guizhenyu
 */
public class CoinsWaySameValueSamePapper {

  public static class Info{
    public int[] coins;
    public int[] counts;

    public Info(int[] c, int[] nums){
      this.coins = c;
      this.counts = nums;
    }

  }


  public static void main(String[] args) {
    int coinsMaxValue = 20;

    int maxNum = 10;

    int testTime = 1000000;

    System.out.println("test start!");

    for (int i = 0; i < testTime; i++){
      int[] arr = randomArray(coinsMaxValue, maxNum);
      int aim = (int)(Math.random() * coinsMaxValue);
      int ways =  violenceRecursive(arr, aim);
      int ways1 = dp1(arr, aim);
      if (ways != ways1){
        System.out.println("Oops!");
        return;
      }

    }

    System.out.println("test success!");
  }

  private static int dp1(int[] arr, int aim) {
    if (aim < 0 || arr == null || arr.length == 0){
      return 0;
    }

    Info info = getInfo(arr);
    int[] coins = info.coins;
    int[] counts = info.counts;
    int N = coins.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;

    for (int index = N - 1; index >= 0; index--){

      for (int rest = 0; rest <= aim; rest++){
        dp[index][rest] = dp[index + 1][rest];
        if (rest >= coins[index]){
          dp[index][rest] += dp[index][rest - coins[index]];
        }
        // todo: 这边还得把超出这个硬币张数范围的数给减掉
        if (rest >= coins[index] * (counts[index] + 1)){
          dp[index][rest] -= dp[index + 1][rest - coins[index] * (counts[index] + 1)];
        }
      }


    }


    return dp[0][aim];

  }

  private static int violenceRecursive(int[] arr, int aim) {

    if (aim < 0 || arr == null || arr.length == 0){
      return 0;
    }

    Info info = getInfo(arr);

    return process(info, 0, aim);
  }

  private static int process(Info info, int index, int rest) {
    if (index == info.coins.length){
      return rest == 0? 1 : 0;
    }

    int ways = 0;

    for (int count = 0; count * info.coins[index] <= rest && count <= info.counts[index]; count++){
      ways += process(info, index + 1, rest - count * info.coins[index]);
    }

    return ways;
  }


  public static Info getInfo(int[] arr){
    if (arr == null || arr.length == 0){
      return null;
    }

    Map<Integer, Integer> record = new HashMap<>();

    for (int i = 0; i < arr.length; i++){
      if (record.containsKey(arr[i])){
        record.put(arr[i], record.get(arr[i]) + 1);
      }else {
        record.put(arr[i], 1);
      }
    }
    int[] coins = new int[record.size()];
    int[] counts = new int[record.size()];
    int index = 0;
    for (Entry<Integer, Integer> entry: record.entrySet()){
      coins[index] = entry.getKey();
      counts[index++] = entry.getValue();
    }
    return new Info(coins, counts);
  }

  // 为了测试
  public static int[] randomArray(int maxLen, int maxValue) {
    int N = (int) (Math.random() * maxLen);
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = (int) (Math.random() * maxValue) + 1;
    }
    return arr;
  }
}
