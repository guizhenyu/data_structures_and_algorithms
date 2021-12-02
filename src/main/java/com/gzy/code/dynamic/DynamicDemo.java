package com.gzy.code.dynamic;

/**
 * description: DynamicDemo date: 2021/8/20 3:51 下午
 *
 * @author: guizhenyu
 */
public class DynamicDemo {

  public static int knapack3(int[] weight, int[] value, int n, int w){
    int[][] states = new int[n][w+1];
    for (int i = 0; i < n; i++){

      for(int j = 0; i < n; i ++){
        states[i][j] = -1;
      }
      states[0][0] = 0;
      if (weight[0] <= w){
        states[0][weight[0]] = value[0];
      }
    }


    for (int i = 1; i < n; ++i){
     for (int j = 0; j <= w; ++j){
       if (states[i - 1][j] >= 0){
         // 不选择第i个物品
         states[i][j] = states[i-1][j];
       }
     }

     for (int j = 0; j <= weight[i]; j++){
       // 选择第i个物品
       if (states[i - 1][j] >= 0){
         int v = states[i - 1][j] + weight[i];
         if (v > states[i][j + weight[i]]){
           states[i][j + weight[i]] = v;
         }
       }
     }
    }


    // 找出最大值
    int maxValue = -1;
    for(int j = 0; j <= w; j++){
      if (states[n - 1][j] > maxValue){
        maxValue = states[n-1][j];
      }
    }
    return maxValue;
  }

}
