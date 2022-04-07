package com.gzy.code.basic.class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * description: BFS date: 2022/4/3 8:36 下午
 *
 * @author: guizhenyu
 */
public class BFS {

  public static void bfs(Node node) {

    if (null == node){
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    HashSet<Node> set = new HashSet<>();
    queue.add(node);
    set.add(node);
    while (!queue.isEmpty()){
      Node poll = queue.poll();
      System.out.println(poll.value);

      List<Node> nexts = poll.nexts;
      if (null == nexts || nexts.isEmpty()){
        continue;
      }
      for (Node next : nexts){
        if (!set.contains(next)){
          set.add(next);
          queue.add(next);
        }
      }
    }
  }

}
