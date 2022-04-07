package com.gzy.code.basic.class16;

/**
 * description: Edge date: 2022/4/3 8:42 下午
 *
 * @author: guizhenyu
 */
public class Edge {

  public int weight;

  public Node from;

  public Node to;

  public Edge(int weight, Node from, Node to){
    this.weight = weight;
    this.from = from;
    this.to = to;
  }

}
