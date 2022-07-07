package com.gzy.code.basic.class16.try20220704;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * description: BFS date: 2022/7/4 16:10
 *
 * @author: guizhenyu
 */
public class BFS {
  /**
   * 宽度优先遍历
   * 通过队列是想，从头节点先放入队列中
   * 队列中弹出值，如果弹出的节点还有子节点，就继续把子节点放入队列中
   * 知道弹出的节点没有子节点，并且队列也已经空了
   *
   */

  public static List<Node> bfs(Node node){
    if (null == node){
      return null;
    }
    Queue<Node> queue = new LinkedList<>();
    Set<Node> set = new HashSet<Node>();
    queue.add(node);
    List<Node> ans = new ArrayList<>();
    while (!queue.isEmpty()){
      Node poll = queue.poll();
      if (null != poll.nexts && poll.nexts.size() > 0){
        for (Node next : node.nexts){
          if (!set.contains(next)){
            queue.add(next);
            set.add(next);
          }

        }
      }
      ans.add(poll);
    }
    return ans;
  }

}
