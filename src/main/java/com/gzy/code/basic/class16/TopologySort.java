package com.gzy.code.basic.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description: TopologySort date: 2022/4/5 2:26 下午
 *
 * @author: guizhenyu
 */
public class TopologySort {


  public static List<Node> sortTop(Graph graph){
    HashMap<Node, Integer> inMap = new HashMap<>();
    Map<Integer, Node> nodeMap = graph.nodes;

    List<Node> ans = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>();
    for (Node node : nodeMap.values()) {
      inMap.put(node, node.in);
      if (node.in == 0){
        queue.add(node);
      }
    }

    while (!queue.isEmpty()){
      Node poll = queue.poll();
      ans.add(poll);
      for (Node node : poll.nexts){
        Integer in = inMap.get(node) - 1;
        inMap.put(node, in);
        if (in == 0){
          queue.add(node);
        }
      }
    }



    return ans;
  }

}
