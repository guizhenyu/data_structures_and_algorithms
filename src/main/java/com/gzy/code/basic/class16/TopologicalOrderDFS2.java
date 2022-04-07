package com.gzy.code.basic.class16;

import com.gzy.code.basic.class16.TopologicalOrderDFS.MysComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * description: TopogicalOrderDFS2 date: 2022/4/5 2:42 下午
 *
 * @author: guizhenyu
 */
public class TopologicalOrderDFS2 {

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
    private DirectedGraphNode node;

    private long nodes;

    public Record(DirectedGraphNode node){
      this.node = node;
      this.nodes = 0;
    }
  }

  public static class MysComparator implements Comparator<Record>{


    @Override
    public int compare(Record o1, Record o2) {
      return o2.nodes == o1.nodes ? 0 : o2.nodes > o1.nodes ? 1 : -1;
    }
  }

  public static ArrayList<DirectedGraphNode> sortTop(List<DirectedGraphNode> graphNodes){
    HashMap<DirectedGraphNode, Record> recordMap = new HashMap<>();
    for (DirectedGraphNode node : graphNodes){
      f(node, recordMap);
    }

    ArrayList<Record> records = new ArrayList<>();
    recordMap.values().stream().map(record -> records.add(record));

    records.sort(new MysComparator());

    ArrayList<DirectedGraphNode> ans = new ArrayList<>();
    for (Record record : records) {
      ans.add(record.node);
    }
    return ans;

  }

  private static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> recordMap) {
    long nodes = 1;
    if (recordMap.containsKey(node)){
      return recordMap.get(node);
    }

    for (DirectedGraphNode next : node.neighbors) {
      nodes += f(next, recordMap).nodes;
    }

    Record record = new Record(node);
    record.nodes += nodes;
    recordMap.put(node, record);
    return record;
  }


}
