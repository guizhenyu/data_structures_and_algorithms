package com.gzy.code.basic.class19.second;

/**
 * description: Knapsack date: 2022/7/26 11:01
 * 背包问题
 * 背包的容量是 bag
 * 总共有 w[] 个物品，每个物品的对应的价值v[]
 *
 * 求最大价值
 * @author: guizhenyu
 */
public class Knapsack {

  public static void main(String[] args) {

    int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
    int[] values = { 5, 6, 3, 19, 12, 4, 2 };
    int bag = 15;

    System.out.println(maxValue(weights, values, bag));
    System.out.println(maxValueDp(weights, values, bag));

  }

  private static int maxValueDp(int[] weights, int[] values, int bag) {
      if (weights == null || values == null || weights.length == 0 || weights.length != values.length){
        return 0;
      }
    int N = weights.length;
    int[][] dp = new int[N + 1][bag + 1];

    for(int i = N - 1; i >= 0; i--){
      for (int rest = 0; rest <= bag; rest++){
        int p1 = dp[i + 1][rest];
        int p2 = 0;
        if (rest >= weights[i]){
          p2 = dp[i + 1][rest - weights[i]] + values[i];

        }

        dp[i][rest] = Math.max(p1, p2);

      }
    }



    return dp[0][bag];

  }

  /**
   * 暴力递归
   * @param weights
   * @param values
   * @param bag
   * @return
   */
  private static int maxValue(int[] weights, int[] values, int bag) {
    if (weights == null || values == null || weights.length == 0 || weights.length != values.length){
      return 0;
    }

    return process(weights, values, 0, bag);

  }

  private static int process(int[] w, int[] v, int index, int bag) {
    // 背包没有剩余了
    if (bag < 0){
      return -1;
    }

    if (index == w.length){
      // 物品已经选完了
      return 0;
    }

    // 不要当前位置的物品
    int p1 = process(w, v, index + 1, bag);
    // 要当前位置的物品
    int p2 = 0;
    int next = process(w, v, index + 1, bag - w[index]);
    if (next != -1){
      p2 = next + v[index];
    }

    return Math.max(p1, p2);

  }

}
