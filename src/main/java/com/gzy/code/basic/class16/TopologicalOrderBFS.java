package com.gzy.code.basic.class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description: TopologicalOrderBFS date: 2022/4/4 3:35 下午
 *
 * @author: guizhenyu
 */
public class TopologicalOrderBFS {

  // 不要提交这个类
  public static class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }

  public static List<DirectedGraphNode> sortTop(List<DirectedGraphNode> graphNodes){

    Map<DirectedGraphNode, Integer> degreeMap = new HashMap<>();
    for (DirectedGraphNode node : graphNodes){
      degreeMap.put(node, 0);
    }
    for (DirectedGraphNode node : graphNodes){
      List<DirectedGraphNode> nexts = node.neighbors;
      for (DirectedGraphNode next : nexts){
        degreeMap.put(next, degreeMap.get(next) + 1);
      }
    }

    Queue<DirectedGraphNode> queue = new LinkedList<>();
    List<DirectedGraphNode> ans = new ArrayList<>();

    for (DirectedGraphNode key : degreeMap.keySet()){
      if (degreeMap.get(key) == 0){
        queue.add(key);
      }
    }

    while (!queue.isEmpty()){
      DirectedGraphNode poll = queue.poll();
      ans.add(poll);
      for (DirectedGraphNode node : poll.neighbors) {
        Integer degree = degreeMap.get(node) - 1;
        if (degree == 0){
          queue.add(node);
        }
        degreeMap.put(node, degree);
      }
    }
    return ans;
  }


}
