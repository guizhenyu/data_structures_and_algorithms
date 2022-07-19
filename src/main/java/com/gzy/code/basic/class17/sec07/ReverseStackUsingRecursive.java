package com.gzy.code.basic.class17.sec07;

import java.util.Stack;

/**
 * description: ReverseStackUsingRecursive date: 2022/7/19 11:42
 *
 * @author: guizhenyu
 */
public class ReverseStackUsingRecursive {

  /**
   * 用递归反转栈
   * 思路：
   * 1. 栈是先进后出的数据结构
   * 2.
   */





  public static void main(String[] args) {
    Stack<Integer> test = new Stack<Integer>();
    test.push(1);
    test.push(2);
    test.push(3);
    test.push(4);
    test.push(5);
    reverse(test);
    while (!test.isEmpty()) {
      System.out.println(test.pop());
    }

  }


  /**
   * 反转栈的方法
   *
   * @param stack
   */
  private static void reverse(Stack<Integer> stack) {

    if (stack.empty()){
      return;
    }
    // 弹出栈低元素,其他元素顺序位置不变
    Integer last = f(stack);

    // 继续反转剩余的元素
    reverse(stack);

    // 将last放到栈顶，就完成了栈里面所有元素的反转
    stack.push(last);
  }

  private static Integer f(Stack<Integer> stack) {
    Integer result = stack.pop();

    if (stack.isEmpty()){
      return result;
    }

    // 获取最下面的元素
    Integer last = f(stack);
    // 其他元素，还是依次放入栈中
    stack.push(result);

    return last;
  }


}
