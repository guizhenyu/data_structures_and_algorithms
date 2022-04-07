package com.gzy.code.basic.class16;

import java.util.ArrayList;
import java.util.List;

/**
 * description: Node date: 2022/4/3 8:40 下午
 *
 * @author: guizhenyu
 */
public class Node {

  public int value;

  public int in;

  public int out;

  public List<Node> nexts;

  public List<Edge> edges;


  public Node(int value){
    this.value = value;
    in = 0;
    out = 0;
    nexts = new ArrayList<>();
    edges = new ArrayList<>();
  }

}
