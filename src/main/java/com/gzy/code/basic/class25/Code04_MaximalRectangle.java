package com.gzy.code.basic.class25;

import java.util.Stack;

/**
 * description: Code04_MaximalRectangle date: 2022/5/1 13:43
 *
 * @author: guizhenyu
 */
public class Code04_MaximalRectangle {

  /**
   * 给定一个二维数组matrix，其中的值不是0就是1，
   * 返回全部由1组成的最大子矩形，内部有多少个1
   */

  public static int maximalRectangle(char[][] map){

    int len = map[0].length;

    int[] heights = new int[len];

    int maxOnes = Integer.MIN_VALUE;
    for (int i = 0; i < map.length; i++){
      for (int j = 0; i < len; j++){
        heights[j] = map[i][j] == '1'? heights[j] + 1 : 0 ;
      }
      maxOnes = Math.max(maxOnes, monotonousStack(heights));
    }

    return maxOnes;

  }

  public static int monotonousStack(int[] heights){
    if (null == heights || heights.length == 0){
      return 0;
    }
    int maxArea = 0;
    int len = heights.length;
    Stack<Integer> stack = new Stack<Integer>();

    for (int i = 0; i < len; i++){
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
        int index = stack.pop();
        int right = i;
        int left = stack.isEmpty()? -1 : stack.peek();
        maxArea = Math.max(maxArea, (right - left - 1) * heights[index]);
      }
      stack.push(i);
    }
    while (!stack.isEmpty()){
      int index = stack.pop();
      int right = len;
      int left = stack.isEmpty()? -1 : stack.peek();
      maxArea = Math.max(maxArea, (right - left - 1) * heights[index]);
    }
    return maxArea;
  }

}
