package com.gzy.code.leecode.dynamic;

import java.util.Stack;

/**
 * description: Trap_42 date: 2022/11/11 11:28
 *  给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *  n == height.length
 *  1 <= n <= 2 * 104
 *  0 <= height[i] <= 105
 * @author: guizhenyu
 */
public class Trap_42 {


  /**
   * 这个题目，个人觉得还是用单调栈比较好解决
   *
   * @param height
   * @return
   */
  public int trap(int[] height) {
    if (height == null || height.length <= 2){
      return 0;
    }

    Stack<Integer> stack = new Stack<Integer>();

    int ans = 0;

    stack.push(0);

    for (int i = 1; i < height.length; i++){
      while (stack.size() > 0 && height[stack.peek()] <= height[i]){
        Integer index = stack.pop();
        if (height[index] == height[i]){
          continue;
        }
        if (stack.size() > 0){
          // todo 这边注意的是要取最短线左右两边的最小边，否则会多算
          int h = Math.min(height[stack.peek()], height[i]);
          ans += (i - stack.peek() - 1) * (h - height[index]);
        }
      }
      stack.push(i);
    }
    return ans;
  }



}
