package com.gzy.code.basic.class16.try20220704;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description: TopologySort date: 2022/7/5 09:44
 *
 * @author: guizhenyu
 */
public class TopologySort {

  /**
   * 拓扑排序：
   * 基于Graph
   * 思路：
   * 遍历他所有的node, 入度为0的数，入栈，他的nexts中的入度 - 1，
   * 一直寻找入度为0的node入栈，知道栈为null
   *
   */

  public void topologySort(Graph graph){
    if (graph == null || graph.indexMap == null){
      return;
    }

    Map<Node, Integer> inMap = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();
    for (Node node : graph.indexMap.values()){
      inMap.put(node, node.in);
      if (node.in == 0){
        queue.add(node);
      }
    }

    while (!queue.isEmpty()){
      Node poll = queue.poll();
      System.out.println(poll.value);
      if (poll.nexts != null && poll.nexts.size() > 0){
        for (Node node : poll.nexts){
          inMap.put(node, inMap.get(node) - 1);
          if (inMap.get(node) == 0){
            queue.add(node);
          }
        }
      }
    }

  }



}
