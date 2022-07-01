package com.gzy.code.basic.class14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * description: UnionFind date: 2022/6/30 13:18
 *
 * @author: guizhenyu
 */
public class UnionFindDemo {
  /**
   * 并查集
   * 1. 每个对象都有一个初始父对象 --> 就是自己
   * 2. 如果两个对象拥有同一个祖（父）节点，就合并这个两个节点的祖节点，并且更新
   *
   *
   */

  public static class Node<V> {
    V value;

    public Node(V v){
      value = v;
    }
  }


  public static class UnionFind<V>{
    // 保存 值对应的节点
    public Map<V, Node<V>> nodes;
    // 保存 节点对应的父节点
    public Map<Node<V>, Node<V>> parents;
    // 保存每个父节点对应的 子节点的个数
    public Map<Node<V>, Integer> sizeMap;


    public UnionFind(List<V> values){
      for (V value : values){
        Node<V> node = new Node<>(value);
        nodes.put(value, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }


    public Node<V> findFather(Node<V> node){
      Node cur = node;
      // 往上遍历父节点时，需要记录遍历过程中遇到的所有的节点
      Stack<Node<V>> stack = new Stack<Node<V>>();
      while (cur != parents.get(cur)){
        stack.push(cur);
        cur = parents.get(cur);
      }
      while (!stack.isEmpty()){
        parents.put(stack.pop(), cur);
      }

      return cur;
    }

    public void union(Node<V> n1, Node<V> n2){
      Node<V> father1 = findFather(n1);
      Node<V> father2 = findFather(n2);
      if (father2 != father1){
        Integer size1 = sizeMap.get(father1);
        Integer size2 = sizeMap.get(father2);

        Node big = size1 >= size2? father1 : father2;
        Node small = big == father1? father2 : father1;
        parents.put(small, big);
        sizeMap.put(big, size1 + size2);
        sizeMap.remove(small);

      }
    }

  }



}
