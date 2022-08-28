package com.gzy.code.basic.class25.second;

import java.util.Stack;

/**
 * description: CountSubmatricesWithAllOnes date: 2022/8/26 15:10
 *
 * @author: guizhenyu
 */
public class CountSubmatricesWithAllOnes {

  public static int numSubmat(int[][] mat) {

    if (mat == null || mat.length == 0 || mat[0].length == 0){
      return 0;
    }


    int n = mat.length;
    int m = mat[0].length;
    int[] heights = new int[m];
    int ans = 0;
    for (int i = 0; i < n; i++){
      for (int j = 0; j < m; j++){
        heights[j] = mat[i][j] == 1? 1 + heights[j] : 0;
        ans += getOnes(heights);

      }
    }

    return ans;
  }

  private static int getOnes(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int ans = 0;
    for (int i = 0; i < heights.length; i++){
      while (stack != null && heights[stack.peek()] >= heights[i]){
        Integer index = stack.pop();

        if (heights[index] > heights[i]){
          int left = stack.isEmpty()? 0 : stack.peek() + 1;
          int len = (i - 1) - left + 1;
          ans = (heights[index] - heights[i]) * ( len * ( len + 1) / 2  );
        }
      }

      stack.push(i);
    }

    while (!stack.isEmpty()){
      Integer pop = stack.pop();
      if (heights[pop] > 0){
        int left = stack.isEmpty()? 0 : stack.peek() + 1;
        int len = heights.length - 1 - left + 1;
        ans += (heights[pop] - (stack.isEmpty()? 0 : heights[stack.peek()])) * (len * (len + 1) / 2);
      }
    }
    return ans;

  }

}
