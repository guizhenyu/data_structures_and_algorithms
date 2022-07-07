package com.gzy.code.basic.class16.try20220704;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description: Graph date: 2022/7/5 09:13
 *
 * @author: guizhenyu
 */
public class Graph {

  public Map<Integer, Node> indexMap;

  public Set<Edge> edges;

  public Graph(){

    indexMap = new HashMap<>();
    edges = new HashSet<>();

  }

}
