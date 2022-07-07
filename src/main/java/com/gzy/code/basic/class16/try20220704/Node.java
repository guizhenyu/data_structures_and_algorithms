package com.gzy.code.basic.class16.try20220704;

import java.util.ArrayList;
import java.util.List;

/**
 * description: Node date: 2022/7/4 13:39
 *
 * @author: guizhenyu
 */
public class Node {

  /**
   * 是每个点信息包括已下三个部分：
   * 1. value  值
   * 2. in     入度（有几个节点指向它）
   * 3. out    出度 (它指向几个节点)
   * 4. nexts  它指向的所有节点
   * 5. edges  它指向的所有的点组成的边
   *
   */

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
