package com.gzy.code.LRU;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 基于哈希表和链表实现LRU算法
 */
public class LRUCache {
    // 1. 定义存储的数据结构（双向链表）
    private class DLinkedNode{
        public int key;
        public int value;
        public DLinkedNode prev;
        public DLinkedNode next;
        public DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    // 2. 保存数据存放位置（对应的链表节点）
    public Map<Integer, DLinkedNode> cache= new HashMap();

    // 记录目前使用的容量大小
    private int size;

    // 容量
    private int capicity;


    // 这边的头尾节点只是用作哨兵，不存储具体数据，只是占位，方便快速定位头尾节点
    // 头节点
    private DLinkedNode head;
    // 尾节点
    private DLinkedNode tail;


    // 构造函数, 初始化LRU缓存容量大小，头尾节点
    public LRUCache(int capicity){
        this.size = 0;
        this.capicity = capicity;
        this.head = new DLinkedNode(-1, -1);
        this.tail = new DLinkedNode(-1, -1);
        this.head.prev = null;
        this.head.next = tail;
        this.tail.prev = head;
        this.tail.next = null;
    }

    // 3. 定义存取方法


    /**
     * 取的方法
     * @param key
     * @return
     */
    public int get(int key){
        //把节点放到头节点
        if(size == 0){
            return -1;
        }

        DLinkedNode node = cache.get(key);
        if (Objects.isNull(node)){
            return -1;
        }

        // 删除节点原来的位置
        removeNode(node);
        // 放到头节点
        addNodeAtHead(node);

        return node.value;
    }

    /**
     * 存放
     *
     * @param key
     * @param value
     */
    public void put(int key, int value){
//        // 1.如果容量满了，删除尾部节点
//        if(size + 1 == capicity){
//            removeNode(tail.prev);
//        }
//
//
//
//        DLinkedNode node = new DLinkedNode(key, value);
//        addNodeAtHead(node);
//        cache.put(key, node);
        DLinkedNode node = cache.get(key);
        if (!Objects.isNull(node)){
            node.value = value;
            // 删除节点原来的位置
            removeNode(node);
            // 放到头节点
            addNodeAtHead(node);
            return;
        }

        if(size == capicity) {

            // 删除节点原来的位置
            cache.remove(tail.prev.key);
            removeNode(tail.prev);
            size--;

        }

        node = new DLinkedNode(key, value);
        addNodeAtHead(node);
        size++;
        cache.put(key, node);
    }


    public void remove(int key){
        if (size == 0){
            return;
        }
        DLinkedNode node = cache.get(key);
        if (Objects.isNull(node)){
            return;
        }
        cache.remove(key);
        removeNode(node);

    }

    /**
     * 把节点放到头节点
     *
     * @param node
     */
    private void addNodeAtHead(DLinkedNode node) {
//        DLinkedNode next = head.next;
//        head.next = node;
//        node.prev = head;
//        next.prev = node;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;

    }

    /**
     * 删除节点
     *
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
