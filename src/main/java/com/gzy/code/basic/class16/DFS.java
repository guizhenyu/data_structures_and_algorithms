package com.gzy.code.basic.class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * description: DFS date: 2022/4/3 9:03 下午
 *
 * @author: guizhenyu
 */
public class DFS {

  public static void dfs(Node node) {

    if (null == node){
      return;
    }

    Stack<Node> stack = new Stack<>();
    HashSet<Node> set = new HashSet<>();
    System.out.println(node.value);
    stack.push(node);
    set.add(node);
    while (!stack.isEmpty()){
      Node pop = stack.pop();
      for (Node next : node.nexts){
        if (!set.contains(next)){
          stack.push(pop);
          stack.push(next);
          set.add(next);
          System.out.println(next.value);
          break;
        }
      }

    }


  }

}
