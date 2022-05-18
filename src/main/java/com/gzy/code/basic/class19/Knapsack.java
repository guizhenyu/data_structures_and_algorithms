package com.gzy.code.basic.class19;

/**
 * description: Knapsack date: 2022/4/14 8:53 上午
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

  private static int maxValue(int[] weights, int[] values, int bag) {

    if (null == weights || null == values ||
        weights.length == 0 ||
    weights.length != values.length){
      return 0;
    }

    return natureWisedom(weights, values, 0, bag);
  }

  /**
   * 自然智慧，暴力递归
   *
   * @param weights 物品的重量数组
   * @param values  物品的价值数组
   * @param index   现在轮到的物品对应数组下标
   * @param restCapacity    背包的剩余容量
   * @return
   */
  private static int natureWisedom(int[] weights, int[] values, int index, int restCapacity) {
    if (restCapacity < 0){
      // 为什么不是0，因为可能有些物品的重量为0
      return -1;
    }
    if (index == weights.length){
      // 下标越界了
      return 0;
    }



    // 有两种可能
    // 第一种情况：当前的物品不要
    // 第二种情况：当前的物品要
    int p1 = natureWisedom(weights, values, index + 1, restCapacity);

    int p2 = 0;
    int next = natureWisedom(weights, values, index + 1, restCapacity - weights[index]);
    if (next >= 0){
      p2 = values[index] + next;
    }
    return Math.max(p1, p2);
  }


  private static int maxValueDp(int[] weights, int[] values, int bag){

    if (null == weights || null == values ||
        weights.length == 0 ||
        weights.length != values.length){
      return 0;
    }
    // 第一维度的范围是 0 ~ N
    // 第二维度的范围就是背包的容量 0 ~ bag
    int[][] dp = new int[weights.length + 1][bag + 1];
    // 下面就是怎么填满这个二维数组
    // 首先参照上面暴力递归的baseCase，得出dp[N][0 ~ bag] = 0;
    // 然后参照暴力递归的计算公式，得出， 每个点的数值都是依赖于他下一行的数据，也就是 index + 1
    int len = weights.length;
    for (int row = len - 1; row >= 0; row--){
      for (int col = 0; col <= bag; col++){
        int p1 = dp[row + 1][col];
        int p2 = 0;
        if (col - weights[row] >= 0){
          p2 = values[row] + dp[row + 1][col - weights[row]];
        }

        dp[row][col] = Math.max(p1, p2);
      }
    }



    return dp[0][bag];
  }
}
