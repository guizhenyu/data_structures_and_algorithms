package com.gzy.code.basic.class16.try20220704;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * description: DFS date: 2022/7/4 16:55
 *
 * @author: guizhenyu
 */
public class DFS {
  public static void dfs(Node head){
    if (null == head){
      return;
    }
    // 栈结构保存节点，先进后出，来实现深度优先遍历
    Stack<Node> stack = new Stack<>();
    Set<Node> set = new HashSet<>();
    System.out.println(head.value);
    set.add(head);
    stack.push(head);

    while (!stack.isEmpty()){
      Node pop = stack.pop();
      if (pop.nexts != null && pop.nexts.size() > 0){
        for (Node node : pop.nexts){
          if (!set.contains(node)){
            // 这边是第一次栈的时候，打印。因为会有节点会重复进栈
            // 重复进栈的目的，深度遍历的时候，会沿着一条边遍历到尾节点，尾节点打印完，需要遍历他的兄弟节点，就需要借助这个重复进栈的父节点达到目的
            System.out.println(node.value);
            stack.push(pop);
            stack.push(node);
            set.add(node);

            break;
          }
        }
      }
    }
  }

}
