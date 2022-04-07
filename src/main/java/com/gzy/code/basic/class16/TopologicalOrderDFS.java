package com.gzy.code.basic.class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * description: TopologicalOrderBFS date: 2022/4/4 3:35 下午
 *
 * @author: guizhenyu
 */
public class TopologicalOrderDFS {

  // 不要提交这个类
  public static class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }


  public static class Record{

    DirectedGraphNode node;
    int deep;

    public Record(int deep, DirectedGraphNode node){
      this.node = node;
      this.deep = deep;
    }
  }

  public static class MysComparator implements Comparator<Record>{

    @Override
    public int compare(Record o1, Record o2) {
      // 降序排列
      return o2.deep - o1.deep;
    }
  }

  public static List<DirectedGraphNode> sortTop(List<DirectedGraphNode> graphNodes){
    Map<DirectedGraphNode, Record> recordMap = new HashMap<>();
    for (DirectedGraphNode node : graphNodes){
      recordMap.put(node, f(node, recordMap));
    }

    ArrayList<Record> records = new ArrayList<>();
    for (Record record : recordMap.values()){
      records.add(record);
    }
    records.sort(new MysComparator());
    ArrayList<DirectedGraphNode> ans = new ArrayList<>();
    int size = records.size();
    for (int i = 0; i < size; i++){
      ans.add(records.get(i).node);
    }
    return ans;

  }


  public static Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> recordMap){
    if (recordMap.containsKey(node)){
      return recordMap.get(node);
    }

    int deep = 0;
    for (DirectedGraphNode next : node.neighbors){
      deep = Math.max(deep, f(next, recordMap).deep);
    }

    Record record = new Record(deep + 1, node);

    recordMap.put(node, record);
    return record;
  }

}
