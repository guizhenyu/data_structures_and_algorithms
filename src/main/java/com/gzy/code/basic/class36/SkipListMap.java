package com.gzy.code.basic.class36;

import java.util.ArrayList;
import java.util.List;

/**
 * description: SkipTreeMap date: 2022/5/24 19:52
 *
 * @author: guizhenyu
 */
public class SkipListMap {

  /**
   * 跳表
   *
   */

  public static class SkipNode<K extends Comparable<K>, V>{
    K key;
    V value;
    List<SkipNode<K, V>> nextNodes;

    public SkipNode(K key, V value){
      this.key = key;
      this.value = value;
      nextNodes = new ArrayList<>();
    }

    public boolean isKeyLess(K otherKey){
      return otherKey != null && (key == null || otherKey.compareTo(key) > 0);
    }

    public boolean isKeyEqual(K otherKey){
      return (key == null && otherKey == null) ||
          (key != null && otherKey != null && key.compareTo(otherKey) == 0);
    }

  }

  public static class SkipList<K extends Comparable<K>, V>{
    private static final double PROBABILITY = 0.5;
    private SkipNode<K, V> head;
    private int size;
    private int maxLevel;


    public SkipList(){
      head = new SkipNode<>(null, null);
      head.nextNodes.add(null);
      this.size = 0;
      this.maxLevel = 0;
    }

    /**
     * 插入新数据时，要一层一层的遍历，
     * 直接找到0层，<key 最右边的节点
     *
     */
    public SkipNode<K, V> mostLessRightNodeInTable(K key){
      if(null == key){
        return null;
      }
      int level = maxLevel;
      SkipNode<K, V> cur = head;
      while (level >= 0){
        cur = mostLessRightNodeInLevel(cur, key, level--);
      }

      return cur;
    }

    private SkipNode<K,V> mostLessRightNodeInLevel(SkipNode<K,V> cur, K key, int level) {
      SkipNode<K, V> next = cur.nextNodes.get(level);
      while (next != null && next.isKeyLess(key)){
        cur = next;
        next = next.nextNodes.get(level);
      }
      return cur;
    }

    public boolean containsKey(K key){
      if (null == key){
        return false;
      }
      SkipNode<K, V> node = mostLessRightNodeInTable(key);
      SkipNode<K, V> next = node.nextNodes.get(0);
      return next == null || next.isKeyEqual(key);
    }

    public void put(K key, V value){
      SkipNode<K, V> node = mostLessRightNodeInTable(key);
      SkipNode<K, V> next = node.nextNodes.get(0);
      if (next != null || next.isKeyEqual(key)){
        next.value = value;
      }else {
        SkipNode<K, V> newNode = new SkipNode<>(key, value);
        int newLevel = 0;
        // 随机确定新节点对应的层数
        while (Math.random() < PROBABILITY){
          newLevel++;
        }
        // 如果新生成的层数大于原来的层数，就补足总层数
        while (maxLevel < newLevel){
          head.nextNodes.add(null);
          maxLevel++;
        }
        // 先默认补齐新节点所以层数的索引
        for (int i = 0; i < newLevel; i++){
          newNode.nextNodes.add(null);
        }

        SkipNode<K, V> pre = head;
        SkipNode<K, V> nextNode = null;
        int curLevel = maxLevel;
        while (curLevel >= 0){
          pre = mostLessRightNodeInLevel(pre, key, curLevel);
          // 这边有个要注意的，是必须要判断一下
          // 因为，如果newLevel，代表新的节点最高的索引层数，而寻找新节点的最邻近值，是从最高层往下一层一层，找到每一层对应的值的
          if (curLevel <= newLevel){
            newNode.nextNodes.set(curLevel, pre.nextNodes.get(curLevel));
            pre.nextNodes.set(curLevel, newNode);
          }
          curLevel--;
        }
      }
    }

    public V get(K key){
      if (null == key){
        return null;
      }
      SkipNode<K, V> pre = mostLessRightNodeInTable(key);
      SkipNode<K, V> next = pre.nextNodes.get(0);
      return next == null? null : next.isKeyEqual(key)? next.value : null;
    }

    /**
     * 1. 一层一层的去删除
     * 2. 如果这一层没了，就把这层给删除了
     *
     *
     * @param key
     */
    public void remove(K key){
      if (null == key || !containsKey(key)){
        return;
      }

      SkipNode<K, V> pre = head;
      int level = maxLevel;
      SkipNode<K, V> next = null;

      while (level >= 0){
        size--;
        pre = mostLessRightNodeInLevel(pre, key, level);
        next = pre.nextNodes.get(level);
        if (next != null && next.isKeyEqual(key)){
          pre.nextNodes.set(level, next.nextNodes.get(level));
        }


        if (level > 0 && pre == head && pre.nextNodes.get(level) == null){
          pre.nextNodes.remove(level);
          level--;
        }
        level--;
      }

    }

    public K firstKey() {
      return head.nextNodes.get(0) == null? null : head.nextNodes.get(0).key;
    }

    public K lastKey(){

      SkipNode<K, V> cur = head;
      int level = maxLevel;
      while (level >= 0){
        SkipNode<K, V> next = cur.nextNodes.get(level);
        while (next != null){
          cur = next;
          next = next.nextNodes.get(level);
        }
        level--;
      }


      return cur.key;
    }

    public K ceilingKey(K key) {
      if (key == null) {
        return null;
      }
      SkipNode<K, V> pre = mostLessRightNodeInTable(key);
      SkipNode<K, V> next = pre.nextNodes.get(0);
      return next != null? next.key : null;
    }

    public K floorKey(K key) {
      if (key == null) {
        return null;
      }
      SkipNode<K, V> pre = mostLessRightNodeInTable(key);
      SkipNode<K, V> next = pre.nextNodes.get(0);
      return next != null? (next.isKeyEqual(key)? next.key : pre.key) : null;

    }

    public int size() {
      return size;
    }

  }

}
