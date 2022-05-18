package com.gzy.code.basic.class17;

import java.util.Stack;

/**
 * description: ReverseStackUsingRecursive date: 2022/4/7 8:39 下午
 *
 * 不借助其他数据结构，实现栈的先进先出功能
 * @author: guizhenyu
 */
public class ReverseStackUsingRecursive {


  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    reverse(stack);
    while (!stack.isEmpty()){
      System.out.println(stack.pop());
    }
  }

  private static void reverse(Stack<Integer> stack) {
    if(stack.isEmpty()){
      return;
    }
    // 返回栈最下面的元素
    Integer lastOne = lastElement(stack);
    reverse(stack);
    stack.push(lastOne);

  }


  private static Integer lastElement(Stack<Integer> stack) {
    Integer result = stack.pop();
    if (stack.isEmpty()){
      return result;
    }else{
      Integer last = lastElement(stack);
      stack.push(result);
      return last;
    }
    
    
  }

}
