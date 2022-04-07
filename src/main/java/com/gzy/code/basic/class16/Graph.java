package com.gzy.code.basic.class16;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * description: Graph date: 2022/4/3 9:40 下午
 *
 * @author: guizhenyu
 */
public class Graph {

  public Map<Integer, Node> nodes;

  public HashSet<Edge> edges;

  public Graph(){
    nodes = new HashMap<>();
    edges = new HashSet<>();
  }


}
