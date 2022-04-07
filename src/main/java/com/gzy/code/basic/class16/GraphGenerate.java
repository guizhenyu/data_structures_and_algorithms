package com.gzy.code.basic.class16;

/**
 * description: GraphGenerate date: 2022/4/3 10:39 下午
 *
 * @author: guizhenyu
 */
public class GraphGenerate {
  // matrix 所有的边
  // N*3 的矩阵
  // [weight, from节点上面的值，to节点上面的值]
  //
  // [ 5 , 0 , 7]
  // [ 3 , 0,  1]
  public static Graph createGraph(int[][] matrix){
    Graph graph = new Graph();
    int row = matrix.length;
    int col = matrix[0].length;
    for (int r = 0; r < row; r++){
      int[] nodeInfo = matrix[r];

      int w = nodeInfo[0];
      int from = nodeInfo[1];
      int to = nodeInfo[2];

      if (graph.nodes.get(from) == null){
        graph.nodes.put(from, new Node(from));
      }
      if (graph.nodes.get(to) == null){
        graph.nodes.put(to, new Node(to));
      }

      Node nodeFrom = graph.nodes.get(from);
      Node nodeTo = graph.nodes.get(to);

      nodeFrom.out++;
      nodeTo.in++;
      nodeFrom.nexts.add(nodeTo);
      Edge edge = new Edge(w, nodeFrom, nodeTo);
      graph.edges.add(edge);
      nodeFrom.edges.add(edge);
    }
    return graph;
  }

}
