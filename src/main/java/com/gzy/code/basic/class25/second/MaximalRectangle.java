package com.gzy.code.basic.class25.second;

import java.util.Stack;

/**
 * description: MaximalRectangle date: 2022/8/25 09:31
 *
 * @author: guizhenyu
 */
public class MaximalRectangle {

  /**
   * 给定一个二维数组matrix，其中的值不是0就是1，
   * 返回全部由1组成的最大子矩形，内部有多少个1
   */
  public static int maximalRectangle(char[][] map){
    int ans = 0;
    int n = map[0].length;
    int[] heights = new int[n];

    for (int i = 0; i < map.length; i++){
      Stack<Integer> stack = new Stack<Integer>();
      for (int j = 0; i < map[i].length; j++){
        int height = map[i][j] == '0'? 0 : (1 + heights[j]);
        heights[j] = height;
        while (!stack.isEmpty() && heights[stack.peek()] >= height){
          Integer pop = stack.pop();
          if (heights[pop] == 0){
            continue;
          }
          int left = stack.isEmpty()? 0 : stack.peek() + 1;
          ans = Math.max(ans, heights[pop] * (j - left));
        }

        stack.push(j);
      }
      while (!stack.isEmpty()){
        Integer pop = stack.pop();
        if (heights[pop] == 0){
          continue;
        }
        int r = map[i].length;
        int l = stack.isEmpty()? 0 : stack.peek() + 1;

        ans = Math.max(ans, heights[pop] * (r - l));
      }

    }

    return ans;
  }

}
