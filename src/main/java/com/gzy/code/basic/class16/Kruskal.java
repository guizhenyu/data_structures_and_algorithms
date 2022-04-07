package com.gzy.code.basic.class16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * description: Kruskal date: 2022/4/5 4:42 下午
 *
 * @author: guizhenyu
 */
public class Kruskal {

  public static class UnionFind{

    public Map<Node, Node> parent;

    public Map<Node, Integer> sizeMap;

    public UnionFind(){
      parent = new HashMap<>();
      sizeMap = new HashMap<>();
    }

    public void makeSet(Collection<Node> nodes){
      parent.clear();
      sizeMap.clear();
      for (Node node : nodes){
        parent.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    public void union(Node from, Node to){
      Node f1 = father(from);
      Node f2 = father(to);

      while (f1 != f2){
        Node greatNode = sizeMap.get(f1) >= sizeMap.get(f2)? f1 : f2;
        Node lessNode = sizeMap.get(f1) < sizeMap.get(f2)? f1 : f2;

        parent.put(lessNode, greatNode);
        sizeMap.put(greatNode, sizeMap.get(lessNode) + sizeMap.get(greatNode));
        sizeMap.remove(lessNode);
      }

    }

    public boolean isSameSet(Node n1, Node n2){
      return father(n1) != father(n2);
    }

    public static class MyEdgeComparator implements Comparator<Edge>{

      @Override
      public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
      }
    }

    public Node father(Node node){

      Stack<Node> stack = new Stack<>();

      while (node != parent.get(node)){
        stack.push(node);
        node = parent.get(node);
      }

      while (!stack.isEmpty()){
        parent.put(stack.pop(), node);
      }
      return node;
    }


    public static Set<Edge> kruskalMST(Graph graph) {
      UnionFind unionFind = new UnionFind();
      unionFind.makeSet(graph.nodes.values());
      HashSet<Edge> edges = graph.edges;
      PriorityQueue<Edge> queue = new PriorityQueue<>(new MyEdgeComparator());

      for (Edge edge : edges) {
        queue.add(edge);
      }
      HashSet<Edge> ans = new HashSet<>();
      while (!queue.isEmpty()){
        Edge edge = queue.poll();

        if (!unionFind.isSameSet(edge.from, edge.to)){

          unionFind.union(edge.from, edge.to);
          ans.add(edge);
        }
      }
      return ans;
    }
  }


}
