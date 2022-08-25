package com.gzy.code.basic.class25.second;

import java.util.Stack;

/**
 * description: LargestRectangleInHistogram date: 2022/8/24 17:05
 *
 * @author: guizhenyu
 */
public class LargestRectangleInHistogram {

  /**
   * 给定一个非负数组arr，代表直方图
   * 返回直方图的最大长方形面积
   *
   * @param heights
   * @return
   */

  public int natureWisdom(int[] heights){
    if (heights == null || heights.length < 1){
      return 0;
    }
    int ans = Integer.MIN_VALUE;
    int n = heights.length;
    for (int i = 0; i < n; i++){
      int height = heights[i];
      for (int h = 1; h <= height; h++){

        int r = i;
        while (r < h && heights[r] >= h){
          r++;
        }

        ans = Math.max(ans, h * (r - i + 2));
      }
    }

    return ans;

  }

  public int monotonousStack(int[] heights){
    if (heights == null || heights.length < 1){
      return 0;
    }

    int ans = Integer.MIN_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    int n = heights.length;

    for (int i = 0; i < n; i++){
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
        Integer pop = stack.pop();
        int left = stack.isEmpty()? 0 : stack.peek() + 1;
        ans = Math.max(heights[pop] * ((i - 1) - left), ans);
      }
      stack.push(i);
    }


    while (!stack.isEmpty()){

      Integer pop = stack.pop();

      int l = stack.isEmpty()? 0 : stack.peek() + 1;
      ans = Math.max(ans, heights[pop] * ((n - 1) - l));

    }

    return ans;
  }

}
