package com.gzy.code.basic.class16;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: Dijkstra date: 2022/4/6 2:49 下午
 *
 * @author: guizhenyu
 */
public class Dijkstra {


  public static HashMap<Node, Integer> dijkstra1(Node head){
    HashMap<Node, Integer> distanceMap = new HashMap<>();
    distanceMap.put(head, 0);
    HashSet<Node> selectedSet = new HashSet<>();
    
    Node minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedSet);
    while (null != minNode){
      int distance = distanceMap.get(minNode);

      List<Edge> edges = minNode.edges;

      for (Edge edge : edges) {
        int weight = edge.weight;
        Node to = edge.to;
        if (!distanceMap.containsKey(to)){
          distanceMap.put(to, distance + weight);
        }else {
          distanceMap.put(to, Math.min(distanceMap.get(to), distance + weight));
        }
      }
      selectedSet.add(minNode);
      minNode = getMinDistanceAndUnSelectedNode(distanceMap, selectedSet);
    }

    return distanceMap;

  }

  public static Node getMinDistanceAndUnSelectedNode(Map<Node, Integer> distanceMap, Set<Node> selectedSet){
    Node minNode = null;
    int min = Integer.MAX_VALUE;

    for (Node node : distanceMap.keySet()) {
      Integer distance = distanceMap.get(node);
      if (!selectedSet.contains(node) && distance < min){
        minNode = node;
        min = distance;
      }
    }
    return minNode;
  }

  // 用加强堆改造dj



  // 加强堆
  public static class NodeHeap{

    /**
     * 普通的堆结构
     *
     * 1. heapfy
     * 2.
     *
     */

    /**
     * 加强堆结构
     *
     * 除了可以直接入堆，还知道自己所处的位置
     *
     */

    // 堆结构
    Node[] nodeHeap;

    // node 对应的堆的索引下标
    Map<Node, Integer> nodeIndexMap;



    // 容量
    int capacity;
    // 堆的大小
    int size;

    public NodeHeap(int size){
      nodeHeap = new Node[size];
      nodeIndexMap = new HashMap<>();
      capacity = size;
    }

    public void push(Node node){
      //
      if (size == capacity){
        return;
      }

      nodeHeap[size] = node;
      nodeIndexMap.put(node, size);
      heapInsert(size++);
    }


    /**
     * 将刚放入堆的元素进行池化
     *
     * @param index
     */
    private void heapInsert(int index) {

      // 跟他的父节点比较
      int parentIndex = (index - 1) / 2;
      while (nodeHeap[index].value < nodeHeap[parentIndex].value){
        swap(index, parentIndex);
        index = parentIndex;
        parentIndex = (index - 1) / 2;
      }

    }

    public void swap(int swapIndex0, int swapIndex1){
      nodeIndexMap.put(nodeHeap[swapIndex0], swapIndex1);
      nodeIndexMap.put(nodeHeap[swapIndex1], swapIndex0);
      Node node = nodeHeap[swapIndex0];
      nodeHeap[0] = nodeHeap[1];
      nodeHeap[1] = node;
    }

    public Node pop() throws Exception {
      if (size == 0){
        throw new Exception("heap is empty");
      }
      Node node = nodeHeap[0];
      swap(0, size - 1);
      nodeHeap[size - 1] = null;
      heapify(0);
      size--;
      return node;
    }

    // 向下堆化
    private void heapify(int index) {

      int left = index * 2 + 1;
      while (left < size){
        int smallerIndex = left + 1 < size && nodeHeap[left].value > nodeHeap[left + 1].value? left + 1 : left;
        if (nodeHeap[index].value <= nodeHeap[smallerIndex].value){
          break;
        }
        swap(index, smallerIndex);
        left = smallerIndex * 2 + 1;
      }

    }

    public void update(Node node){
      if (!nodeIndexMap.containsKey(node)){
        return;
      }
      Integer index = nodeIndexMap.get(node);
      heapInsert(index);
      heapify(index);

    }

    public void remove(Node node){
      if (!nodeIndexMap.containsKey(node)){
        return;
      }
      Integer index = nodeIndexMap.get(node);
      swap(index, size - 1);
      nodeHeap[size - 1] = null;
      size--;
      heapify(index);

    }

    public void batchPush(Collection<Node> nodes){
      for (Node node: nodes) {
        push(node);
      }
    }

    public boolean isEmpty() {
      return size == 0;
    }
  }

  public static HashMap<Node, Integer> dijkstra2(Node head, int size) throws Exception {
    NodeHeap nodeHeap = new NodeHeap(size);
    head.value = 0;
    nodeHeap.push(head);

    HashMap<Node, Integer> ans = new HashMap<>();
    while (!nodeHeap.isEmpty()){
      Node pop = nodeHeap.pop();
      int distance = pop.value;
      ans.put(pop, pop.value);

      for (Edge edge : pop.edges){
        int weight = edge.weight;
        Node to = edge.to;
        if (nodeHeap.nodeIndexMap.containsKey(to)){
          if (to.value > distance + weight){
            to.value = distance + weight;
            nodeHeap.update(to);
          }
        }else {
          to.value = distance + weight;
          nodeHeap.push(to);
        }

      }
    }
    return ans;
  }
}
