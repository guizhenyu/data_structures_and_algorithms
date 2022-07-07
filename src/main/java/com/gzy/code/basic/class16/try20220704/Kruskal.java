package com.gzy.code.basic.class16.try20220704;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * description: Kruskal date: 2022/7/5 11:35
 *
 * @author: guizhenyu
 */
public class Kruskal {
  // K 算法

  public static class Union {
    public Map<Node, Integer> sizeMap;
    public Map<Node, Node> parentMap;


    public Union(Collection<Node> nodes){
      sizeMap = new HashMap<>(nodes.size());
      parentMap = new HashMap<>(nodes.size());
      for (Node node : nodes){
        sizeMap.put(node, 1);
        parentMap.put(node, node);
      }
    }

    public Node findFather(Node node){
      Node cur = node;
      Stack<Node> stack = new Stack<Node>();
      while (cur != parentMap.get(cur)){
        stack.push(cur);
        cur = parentMap.get(cur);
      }

      while (!stack.isEmpty()){
        parentMap.put(stack.pop(), cur);
      }

      return cur;
    }

    public void union(Node nodeA, Node nodeB){
      Node fatherA = findFather(nodeA);
      Node fatherB = findFather(nodeB);

      if (fatherB != fatherA){
        Integer sizeA = sizeMap.get(fatherA);
        Integer sizeB = sizeMap.get(fatherB);

        Node big = sizeA >= sizeB? fatherA : fatherB;
        Node small = sizeA >= sizeB? fatherB: fatherA;

        parentMap.put(small, big);

        sizeMap.put(big, sizeA + sizeB);
        sizeMap.remove(small);
      }
    }
  }

  public class UnionComp implements Comparator<Edge>{

    @Override
    public int compare(Edge o1, Edge o2) {
      return o1.weight - o2.weight;
    }
  }

  public Set<Edge> kruskal(Graph graph){
    Collection<Node> values = graph.indexMap.values();
    Union union = new Union(values);
    PriorityQueue<Edge> heap = new PriorityQueue<>(new UnionComp());
    Set<Edge> ans = new HashSet<>();
    for (Edge edge : graph.edges){
      heap.add(edge);
    }
    while (!heap.isEmpty()){
      Edge poll = heap.poll();
      Node from = poll.from;
      Node to = poll.to;
      Node fatherFrom = union.findFather(from);
      Node fatherTo = union.findFather(to);
      if (fatherFrom != fatherTo){
        union.union(from, to);
        ans.add(poll);
      }
    }


    return ans;


  }
}
