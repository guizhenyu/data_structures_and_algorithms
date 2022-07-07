package com.gzy.code.basic.class16.try20220704;

/**
 * description: GraphGenerate date: 2022/7/5 09:16
 *
 * @author: guizhenyu
 */
public class GraphGenerate {

  /**
   * 将一个二维数组，转化成Graph
   * 二维数组，第二维大小为3
   * 第一个代表权重
   * 第二个代表 from
   * 第三个代表 to
   *
   *
   */

  public Graph generateGraph(int[][] matrix){
    if (matrix == null || matrix.length == 0){
      return null;
    }

    Graph graph = new Graph();

    for (int i = 0; i < matrix.length; i++){
      int weight = matrix[i][0];
      int from = matrix[i][1];
      int to = matrix[i][2];

      Node nodeFrom = graph.indexMap.get(from);
      Node nodeTo = graph.indexMap.get(to);
      if (nodeFrom == null){
        nodeFrom = new Node(from);
        graph.indexMap.put(from, nodeFrom);
      }
      if (nodeTo == null){
        nodeTo = new Node(to);
        graph.indexMap.put(to, nodeTo);
      }

      Edge edge = new Edge(nodeFrom, nodeTo, weight);
      nodeFrom.out++;
      nodeTo.in++;
      nodeFrom.nexts.add(nodeTo);
      nodeFrom.edges.add(edge);
      graph.edges.add(edge);

    }
    return graph;
  }

}
