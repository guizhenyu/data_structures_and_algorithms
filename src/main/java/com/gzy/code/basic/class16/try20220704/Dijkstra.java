package com.gzy.code.basic.class16.try20220704;

import com.gzy.code.basic.class16.Dijkstra.NodeHeap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description: Dijkstra date: 2022/7/13 15:30
 *
 * @author: guizhenyu
 */
public class Dijkstra {


  public Map<Node, Integer> getMinDistance(Node head){
    Map<Node, Integer> result = new HashMap<>();

    Map<Node, Integer> distanceMap = new HashMap<>();
    Set<Node> selectedSet = new HashSet<>();

    distanceMap.put(head, 0);
    Node minNode = getMinDistanceNode(distanceMap, selectedSet);
    while (minNode != null){

      List<Edge> edges = minNode.edges;
      for (Edge edge : edges){
        Node toNode = edge.to;
        // 获取到toNode的最小距离
        int toNodeD = distanceMap.get(toNode) == null? 0 : distanceMap.get(toNode);
        int minD = Math.min(toNodeD, distanceMap.get(minNode) + edge.weight);
        distanceMap.put(toNode, minD);
      }
      result.put(minNode, distanceMap.get(minNode));
      minNode = getMinDistanceNode(distanceMap, selectedSet);
    }

    return result;
  }


  /**
   * 从distanceMap获取，value最小的节点，并且不存在selectedNodes
   *
   * @param distanceMap
   * @param selectedNodes
   * @return
   */
  public Node getMinDistanceNode(Map<Node, Integer> distanceMap, Set<Node> selectedNodes){
    Node minNode = null;
    Integer min = Integer.MAX_VALUE;
    for (Node node : distanceMap.keySet()){
      if (!selectedNodes.contains(node) && distanceMap.get(node) < min){
        min = distanceMap.get(node);
//        selectedNodes.add(node);
        minNode = node;
      }
    }

    return minNode;
  }


  public static class MyHeap{
    /**
     * 加强堆
     * 堆结构：
     *
     */
    public Node[] nodeHeap;

    public Map<Node, Integer> indexMap;

    public int capacity;

    public int size;

    public MyHeap(int size){
      capacity = size;
      this.size = 0;
      nodeHeap = new Node[size];
      indexMap = new HashMap<>(size);
    }

    public void push(Node node){
      nodeHeap[size] = node;
      indexMap.put(node, size);
      heapInsert(size++);

    }

    /**
     *
     * 当前的节点跟父节点比较，比父节点小就交换，一直达到不满足为止
     * @param index
     */
    public void heapInsert(int index) {


      while (nodeHeap[index].value < nodeHeap[(index - 1) / 2].value){
        swap(index, (index - 1) / 2);
        index = (index - 1) / 2;
      }

    }

    public void remove(Node node){
      if(!indexMap.containsKey(node)){
        return;
      }
      Integer index = indexMap.get(node);
      swap(index, size);
      indexMap.remove(node);

      nodeHeap[size] = null;
      size--;

      heapify(index);
    }

    /**
     * 向下堆化
     *
     * @param index
     */
    public void heapify(int index){
      int left = index * 2;

      while (left < size){
        int lessIndex = left + 1 <= size && nodeHeap[left].value <= nodeHeap[left + 1].value ? left : left + 1;
        if (nodeHeap[lessIndex].value > nodeHeap[index].value){
          break;
        }
        swap(lessIndex, index);
        index = lessIndex;
        left = index * 2;
      }

    }



    public Node pop(){
      Node node = nodeHeap[0];

      swap(0, size - 1);

      nodeHeap[size - 1] = null;
      size--;
      heapify(0);
      return node;


    }

    public void update(Node node){
      if(!indexMap.containsKey(node)){
        return;
      }

      Integer index = indexMap.get(node);
      heapInsert(index);
      heapify(index);

    }



    public void swap(int index1, int index2){
      indexMap.put(nodeHeap[index1], index2);
      indexMap.put(nodeHeap[index2], index1);
      Node node1 = nodeHeap[index1];
      nodeHeap[index1] = nodeHeap[index2];
      nodeHeap[index2] = node1;
    }


    public boolean isEmpty() {
      return size == 0;
    }
  }

  public static Map<Node, Integer> dijkstra1(Node head, int size){
    MyHeap nodeHeap = new MyHeap(size);
    Map<Node, Integer> result = new HashMap<>();
//    Set<Node> selectedNode = new HashSet<>();
    nodeHeap.push(head);
    while (!nodeHeap.isEmpty()){
      // 头结点到目前节点，距离最小的点，也就是小根堆的堆顶
      Node pop = nodeHeap.pop();
//      selectedNode.add(pop);
      result.put(pop, pop.value);
      List<Edge> edges = pop.edges;
      int value = pop.value;
      for (Edge edge : edges) {
        Node to = edge.to;

        if (nodeHeap.indexMap.containsKey(to)) {
            to.value = Math.min(value + edge.weight, to.value);
            nodeHeap.update(to);
        } else {
          to.value = value + edge.weight;
          nodeHeap.push(to);
        }
      }
    }

    return result;
  }

}
