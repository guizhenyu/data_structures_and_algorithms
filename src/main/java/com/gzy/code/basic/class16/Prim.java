package com.gzy.code.basic.class16;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * description: Prim date: 2022/4/6 1:24 下午
 *
 * @author: guizhenyu
 */
public class Prim {


  public static class EdgeComparator implements Comparator<Edge>{

    @Override
    public int compare(Edge o1, Edge o2) {
      return o1.weight - o2.weight;
    }
  }

  public static Set<Edge> primMST(Graph graph) {
    // 解锁的边
    HashSet<Edge> edges = new HashSet<>();

    // 解锁点
    HashSet<Node> nodes = new HashSet<>();
    PriorityQueue<Edge> queue = new PriorityQueue<>(new EdgeComparator());
    for (Node node : graph.nodes.values()){
      if (!nodes.contains(node)){

        for (Edge edge : node.edges){
          queue.add(edge);
        }

        while (!queue.isEmpty()){
          Edge poll = queue.poll();
          Node to = poll.to;
          if (!nodes.contains(to)){
            edges.add(poll);
            nodes.add(to);
            for (Edge toEdge : to.edges){
              queue.add(toEdge);
            }
          }


        }

      }


    }

    return edges;
  }

}
