package com.gzy.code.basic.class36;

/**
 * description: SIzeBalacedTreeMap date: 2022/5/21 18:03
 *
 * @author: guizhenyu
 */
public class SizeBalancedTreeMap {

  /**
   * sbt: 是中国的一个高中生，发明的一种搜索平衡二叉树
   *      相比于AVL,他的特点
   *      1. 是不是每个增删操作都触发平衡机制，只有新增的时候触发，从而避免了频繁的旋转操作，提高了操作性能
   *      2. 他的平衡机制是： 每个节点的子树的节点个数 <= 他的兄弟的节点个数，或者说 叔叔的节点数 >= 侄子的节点数
   *      3. 平衡的机制：也是通过左旋和右旋操作实现的
   */


  public static class SBTNode<K extends Comparable<K>, V>{

    public K key;
    public V value;

    public SBTNode l;
    public SBTNode r;
    public int size;

    public SBTNode(K k, V v){
      key = k;
      value = v;
      size = 1;
    }

  }

  public static class SizeBalancedTree<K extends Comparable<K>, V>{
    private SBTNode<K, V> root;


    public SBTNode<K, V> rightRotate(SBTNode<K, V> cur){
      SBTNode<K, V> l = cur.l;
      cur.l = l.r;
      l.r = cur;
      l.size = cur.size;
      cur.size = (cur.l == null? 0 : cur.l.size) + (cur.r == null? 0 : cur.r.size) + 1;
      return l;
    }

    public SBTNode<K, V>  leftRotate(SBTNode<K, V> cur){
      SBTNode r = cur.r;
      cur.r = r.l;
      r.l = cur;
      r.size = cur.size;
      cur.size = (cur.l == null? 0 : cur.l.size) + (cur.r == null? 0 : cur.r.size) + 1;
      return r;
    }

    public SBTNode<K, V> add(SBTNode<K, V> cur, K key, V value){
      if (null == cur){
        return new SBTNode<>(key, value);
      }
      cur.size++;
      if (cur.key.compareTo(key) > 0){
        cur.l = add(cur.l, key, value);
      }else {
        cur.r = add(cur.r, key, value);
      }
      return maintain(cur);
    }
    public SBTNode<K, V> delete(SBTNode<K, V> cur, K key){
      cur.size--;
      if (cur.key.compareTo(key) > 0){
        cur.l = delete(cur.l, key);
      }else if(cur.key.compareTo(key) < 0){
        cur.r = delete(cur.r, key);
      }else {
        //删除当前节点
        if (cur.l == null && cur.r == null){
          cur = null;
        }else if(cur.l == null && cur.r != null){
          cur = cur.r;
        }else if(cur.l != null && cur.r == null){
          cur = cur.l;
        }else {
          // 取右节点的最左节点

          SBTNode<K, V> des = cur.r;
          SBTNode<K, V> pre = null;
          while (des.l != null){
            des.size--;
            pre = des;
            des = des.l;
          }
          if (pre != null){
            pre.l = des.r;
            des.r = cur.r;
          }
          des.l = cur.l;
          des.size = des.l.size + (des.r == null? 0 : des.r.size) + 1;
          cur = des;
        }

      }
      return cur;
    }

    public SBTNode<K, V> maintain(SBTNode<K, V> cur){
      if (null == cur){
        return cur;
      }

      // 左右节点都有
      int ls = cur.l == null? 0 : cur.l.size;
      int rs = cur.r == null? 0 :cur.r.size;
      int lls = (cur.l != null && cur.l.l != null)? cur.l.l.size : 0;
      int lrs = (cur.l != null && cur.l.r != null)? cur.l.r.size : 0;
      int rls = (cur.r != null && cur.r.l != null)? cur.r.l.size : 0;
      int rrs = (cur.r != null && cur.r.r != null)? cur.r.r.size : 0;

      if (lls > rs){
        // 这种情况包括，还包括 lls > rs && lrs > rs
        // 当前节点右转
        cur = rightRotate(cur);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(lrs > rs){
        // 这种情况的前提是 lls <= rs
        // 左节点先左转，cur右转
        cur.l = leftRotate(cur.l);
        cur.l = maintain(cur.l);
        cur = rightRotate(cur);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(rrs > ls){
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      }else if(rls > ls){
        cur.r = rightRotate(cur.r);
        cur.r = maintain(cur.r);
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      }
      // 以上四种情况，没有交叉

      return cur;
    }

    /**
     * 寻找key附近的节点，包括等于key的
     * @param key
     * @return
     */
    private SBTNode<K, V> findLastIndex(K key) {
      SBTNode<K, V> pre = null;
      SBTNode<K, V> cur = root;
      while (cur != null){
        pre = cur;
        if (cur.key.compareTo(key) == 0){
          break;
        }else if(cur.key.compareTo(key) > 0){
          cur = cur.l;
        }else {
          cur = cur.r;
        }
      }

      return pre;
    }

    /**
     * 寻找key右边最近的数，包括他自己
     * @param key
     * @return
     */
    private SBTNode<K, V> findLastNoSmallIndex(K key) {
      SBTNode<K, V> ans = null;
      SBTNode<K, V> cur = root;
      while (cur != null){

        if (cur.key.compareTo(key) == 0){
          ans = cur;
          break;
        }else if(cur.key.compareTo(key) > 0){
          ans = cur;
          cur = cur.l;
        }else {
          cur = cur.r;
        }
      }

      return ans;
    }

    private SBTNode<K, V> findLastNoBigIndex(K key) {
      SBTNode<K, V> ans = null;
      SBTNode<K, V> cur = root;
      while (cur != null){

        if (cur.key.compareTo(key) == 0){
          ans = cur;
          break;
        }else if(cur.key.compareTo(key) > 0){
          cur = cur.l;
        }else {
          ans = cur;
          cur = cur.r;
        }
      }

      return ans;
    }

    /**
     * 根据索引获取元素
     * @param cur
     * @param kth
     * @return
     */
    private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
      if (kth == (cur.l == null? 0 : cur.l.size) + 1 ){
          return cur;
      }
      if (kth <= (cur.l == null? 0 : cur.l.size)){
        return getIndex(cur.l, kth);
      }else {
        return getIndex(cur.r, kth - (cur.l == null? 0 : cur.l.size) - 1);
      }
    }

    public int size() {
      return root == null ? 0 : root.size;
    }


    public boolean containsKey(K key) throws Exception {
      if (key == null){
        throw new Exception("parameter can not be null");
      }
      SBTNode<K, V> sbtNode = findLastIndex(key);
      return sbtNode == null? false :
          sbtNode.key.compareTo(key) == 0? true:false;
    }

    public void put(K key, V value) throws Exception {
      if (key == null){
        throw new Exception("parameter can not be null");
      }
      SBTNode<K, V> sbtNode = findLastIndex(key);
      if (sbtNode != null && sbtNode.key.compareTo(key) == 0){
        sbtNode.value = value;
      }else {
        add(root, key, value);
      }

    }
    public void remove(K key) throws Exception {
      if (key == null){
        return;
      }
      boolean containKey = containsKey(key);
      if (containKey){
        delete(root, key);
      }

    }

    public K getIndexKey(int index) {
      if (index < 0 || index >= this.size()) {
        throw new RuntimeException("invalid parameter.");
      }
      return getIndex(root, index + 1).key; // 这边的＋1 是应为，数的结构是索引下表从1开始的，0不算
    }

    public V getIndexValue(int index) {
      if (index < 0 || index >= this.size()) {
        throw new RuntimeException("invalid parameter.");
      }
      return getIndex(root, index + 1).value; // 这边的＋1 是应为，数的结构是索引下表从1开始的，0不算
    }

    public V get(K key) {
      if (key == null) {
        throw new RuntimeException("invalid parameter.");
      }
      SBTNode<K, V> lastIndex = findLastIndex(key);
      if (null != lastIndex && lastIndex.key.compareTo(key) == 0){
        return lastIndex.value;
      }
      return null;
    }

    public K firstKey() {
      if (root == null){
        return null;
      }
      SBTNode<K, V> left = root;
      while (left.l != null){
        left = left.l;
      }
      return left.key;
    }

    public K lastKey() {
      if(root == null){
        return null;
      }
      SBTNode<K, V> right = root;
      while (right.r != null){
        right = right.r;
      }
      return right.key;
    }

    public K floorKey(K key) {
      if (key == null){
        throw new RuntimeException("invalid parameter.");
      }

      SBTNode<K, V> result = findLastNoBigIndex(key);
      return result == null? null : result.key;
    }

    public K ceilingKey(K key) {
      if (key == null){
        throw new RuntimeException("invalid parameter.");
      }

      SBTNode<K, V> result = findLastNoSmallIndex(key);
      return result == null? null : result.key;
    }
  }


}
