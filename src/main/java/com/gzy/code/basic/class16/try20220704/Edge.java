package com.gzy.code.basic.class16.try20220704;

/**
 * description: Edge date: 2022/7/4 16:08
 * 边结构
 * @author: guizhenyu
 */
public class Edge {

  Node from;

  Node to;

  int weight;

  public Edge(Node from, Node to, int weight){
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

}
