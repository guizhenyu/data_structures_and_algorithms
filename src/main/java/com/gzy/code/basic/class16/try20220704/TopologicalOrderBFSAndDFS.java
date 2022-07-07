package com.gzy.code.basic.class16.try20220704;

import com.gzy.code.basic.class16.TopologicalOrderBFS;
import com.gzy.code.basic.class16.TopologicalOrderBFS.DirectedGraphNode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description: TopologicalOrderBFSAndDFS date: 2022/7/7 11:28
 *
 * @author: guizhenyu
 */
public class TopologicalOrderBFSAndDFS {

  // 不要提交这个类
  public static class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }

  /**
   * 根据上面的拓扑结构
   * 分别进行宽度优先遍历跟深度优先遍历
   *
   * 宽度优先遍历：
   * 类似于，一直寻找入度为0的节点
   *
   *
   * 深度优先遍历
   * 创建一个数据结构，记录每个节点的有哪些后续节点都经过他，也就是深度
   *
   *
   */

  public static List<DirectedGraphNode> bfs(List<DirectedGraphNode> graphNodes){
    Map<DirectedGraphNode, Integer> inMap = new HashMap<>();
    for (DirectedGraphNode node : graphNodes){
      inMap.put(node, 0);
    }

    for (DirectedGraphNode node : graphNodes){
      ArrayList<DirectedGraphNode> neighbors = node.neighbors;
      for (DirectedGraphNode next : neighbors){
        inMap.put(next, inMap.get(next) + 1);
      }
    }

    Queue<DirectedGraphNode> queue = new LinkedList<>();
    List<DirectedGraphNode> ans = new ArrayList<>();

    for (DirectedGraphNode node : inMap.keySet()){
      if (inMap.get(node) == 0){
        queue.add(node);
      }
    }


    while (!queue.isEmpty()){
      DirectedGraphNode poll = queue.poll();
      ans.add(poll);
      if (poll.neighbors != null && poll.neighbors.size() > 0){
        for (DirectedGraphNode next : poll.neighbors){
          inMap.put(next, inMap.get(next) - 1);
          if (inMap.get(next) == 0){
            queue.add(next);
          }
        }
      }

    }


    return ans;
  }

  public static List<DirectedGraphNode> dfs(List<DirectedGraphNode> graphNodes) {
    Map<DirectedGraphNode, Record> recordMap = new HashMap<>();

    for (DirectedGraphNode node : graphNodes){
      recordMap.put(node, f(node, recordMap));
    }
    List<Record> records = new ArrayList<>();
    for(Record record : recordMap.values()){
      records.add(record);
    }
    records.sort(new RecordComp());
    List<DirectedGraphNode> ans = new ArrayList<>();
    for (int i = 0; i < records.size(); i++) {
      ans.add(records.get(i).node);
    }
    return ans;
  }

  /**
   * 获取深度记录
   * @param node
   * @param recordMap
   * @return
   */
  private static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap) {
    if (recordMap.containsKey(node)){
      return recordMap.get(node);
    }


    int deep = 0;

    for (DirectedGraphNode next : node.neighbors){
      deep = Math.max(deep, (int)f(next, recordMap).deep);
    }

    Record record = new Record(node, (long)(deep + 1));

    return record;
  }

  public static class Record{
    public DirectedGraphNode node;
    
    public long deep;
    
    
    public Record(DirectedGraphNode node, long deep){
      this.node = node;
      this.deep = deep;
    }
    
  }
  
  public static class RecordComp implements Comparator<Record>{

    @Override
    public int compare(Record o1, Record o2) {
      return (int)(o2.deep - o1.deep);
    }
  }
}
