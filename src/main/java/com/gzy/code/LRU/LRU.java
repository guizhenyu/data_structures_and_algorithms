package com.gzy.code.LRU;

import java.util.HashMap;
import java.util.Objects;

/**
 * description: LRU date: 2021/7/29 3:36 下午
 * 用HashMap和 链表实现 O(1)的 LRU
 * @author: guizhenyu
 */
public class LRU {
  /**
   * 思路：
   * Least Recently Used 最近最少淘汰算法
   * 实现总共分两步：
   * 1. 记录最新使用key ，O(1)
   * 2. 容量满了后，怎么去淘汰使用时间最远的那个key O(1)
   *
   * 定义结构：
   *   成员变量：
   *     容量：size
   *     实现查找时间复杂度是O(1)的key的数据结构：HashMap
   *     记录key的顺序的结构：自定义一个双向链表
   *
   */

  /**
   * 当前使用容量
   */
  private Integer size;

  /**
   * 容量
   */
  private Integer capicity;

  /**
   * 定义HashMap记录key和Node的关系
   *
   */
  private HashMap<String, LRUCacheNode> cache = new HashMap<>();


  /**
   * 这里的头结点和尾节点，不存储任何数据，起哨兵的作用
   *
   */
  private LRUCacheNode head;
  private LRUCacheNode tail;

  /**
   * 双向链表
   *
   */
  public class LRUCacheNode{

    private LRUCacheNode prev;

    private LRUCacheNode next;

    private String key;

    private String value;

    public LRUCacheNode(String key, String value){
      this.key = key;
      this.value = value;
    }

  }

  public LRU(int capicity){

    this.capicity = capicity;
    this.size = 0;
    this.head = new LRUCacheNode("-1", "-1");
    this.tail = new LRUCacheNode("-1", "-1");
    head.prev = null;
    head.next = tail;
    tail.prev = head;
    tail.prev = null;

  }

  public String get(String key){
    LRUCacheNode lruCacheNode = cache.get(key);
    if (Objects.isNull(lruCacheNode)){
      return "";
    }
    // 删除该节点
    remove(lruCacheNode);
    // 放到头部
    head.next.prev = lruCacheNode;
    head.next = lruCacheNode;
    return lruCacheNode.value;
  }

  public void put(String key, String value){
    LRUCacheNode lruCacheNode = cache.get(key);
    if (Objects.isNull(lruCacheNode)){
      lruCacheNode = new LRUCacheNode(key, value);

    }else{
       // 删除原节点
      removeNode(lruCacheNode);
      lruCacheNode.value = value;
    }

    if (size.equals(capicity)){
      // 删除尾节点
      removeNode(tail.prev);
      cache.remove(tail.prev.key);
    }
    // 将节点放入头节点前

    lruCacheNode.next = head.next;
    head.next.prev = lruCacheNode;
    lruCacheNode.prev = head;
    head.next = lruCacheNode;

    cache.put(key, lruCacheNode);
    size++;
  }



  public void removeNode(LRUCacheNode node){
    // 删除原节点
    remove(node);
    size --;
  }

  public void remove(LRUCacheNode node){
    // 删除原节点
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }






}
