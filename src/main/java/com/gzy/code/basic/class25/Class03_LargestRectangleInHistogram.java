package com.gzy.code.basic.class25;

import java.util.Stack;

/**
 * description: Class03_LargestRectangleInHistogram date: 2022/5/1 11:43
 *
 * @author: guizhenyu
 */
public class Class03_LargestRectangleInHistogram {

  /**
   * 给定一个非负数组arr，代表直方图
   * 返回直方图的最大长方形面积
   *
   * @param heights
   * @return
   */

  public static int natureWisdom(int[] heights){
    int maxArea = Integer.MIN_VALUE;
    int len = heights.length;
    for (int i = 0; i < len; i++){
      int height = heights[i];
      for (int h = 1; h <= height; h++){

        int r = i;
        while (r < len && heights[r] >= h){
          r++;
        }

        int width = r - i + 1;

        maxArea = Math.max(maxArea, h * width);
      }
    }
    return maxArea;
  }

  public static int monotonousStack(int[] heights){

    int maxArea = Integer.MIN_VALUE;
    int len = heights.length;

    Stack<Integer> stack = new Stack<Integer>();

    for (int i = 0; i < len; i++){
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
        Integer index = stack.pop();
        int left = stack.isEmpty() ? -1 : stack.peek();
        maxArea = Math.max(maxArea, (i - left - 1) * heights[index]);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()){
      Integer index = stack.pop();
      int left = stack.isEmpty() ? -1 : stack.peek();
      maxArea = Math.max(maxArea, (len - left - 1) * heights[index]);
    }
    return maxArea;
  }
}
