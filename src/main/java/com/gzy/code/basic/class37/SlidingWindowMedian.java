package com.gzy.code.basic.class37;

/**
 * description: SlidingWindowMedian date: 2022/5/27 06:40
 *
 * @author: guizhenyu
 */
public class SlidingWindowMedian {


  public static double[] medianSlidingWindow(int[] nums, int k) throws Exception {
    SizeBalancedTreeMap<Node> sbt = new SizeBalancedTreeMap<>();

    for (int i = 0; i < k - 1; i++){
      sbt.add(new Node(i, nums[i]));
    }

    double[] ans = new double[nums.length - k + 1];
    int ansIndex = 0;
    for (int i = k - 1; i < nums.length; i++){
      sbt.add(new Node(i, nums[i]));
      if (k % 2 == 0){
        int lowerMedian = sbt.getIndexKey(k / 2).value;
        int upperMedian = sbt.getIndexKey(k / 2 + 1).value;
        ans[ansIndex++] = (lowerMedian + upperMedian) / 2;
      }else {
        ans[ansIndex++] = sbt.getIndexKey(k / 2).value;
      }
      int removeIndex = i - k + 1;
      sbt.remove(new Node(removeIndex, nums[removeIndex]));
    }
    return ans;
  }


  public static class Node implements Comparable<Node>{

    private int index;

    private int value;

    public Node(int index, int value){
      this.index = index;
      this.value = value;
    }

    @Override
    public int compareTo(Node o) {
      return value != o.value? o.value - value : o.index - index;
    }
  }

  public static class SBTNode<K extends Comparable<K>>{

    public K key;

    public SBTNode<K> l;

    public SBTNode<K> r;

    public int size;

    public SBTNode(K key){
      this.key = key;
      size = 1;
    }

  }

  public static class SizeBalancedTreeMap<K extends Comparable<K>>{

    private SBTNode<K> root;

    public int size() {
      return root == null ? 0 : root.size;
    }
    public SBTNode<K> rightRotate(SBTNode<K> cur){
      SBTNode<K> l = cur.l;
      cur.l = l.r;
      l.r = cur;
      cur.size = (cur.l == null? 0 : cur.l.size) + (cur.r == null? 0 : cur.r.size) + 1;
      l.size = cur.size + (l.l == null? 0 : l.l.size) + 1;
      return l;
    }

    public SBTNode<K> leftRotate(SBTNode<K> cur){
      SBTNode<K> r = cur.r;
      cur.r = r.l;
      r.l = cur;
      cur.size = (cur.l == null? 0 : cur.l.size) + (cur.r == null? 0 : cur.r.size) + 1;
      r.size = cur.size + (r.r == null? 0 : r.r.size) + 1;
      return r;
    }

    public SBTNode<K> maintain(SBTNode<K> cur){

      if(cur ==null){
        return null;
      }
      int ls = cur.l == null? 0 : cur.l.size;
      int lls = cur.l == null? 0 :
          cur.l.l == null? 0 : cur.l.l.size;
      int lrs = cur.l == null? 0 :
          cur.l.r == null? 0 : cur.l.r.size;
      int rs = cur.r == null? 0 : cur.r.size;
      int rrs = cur.r == null? 0 :
          cur.r.r == null? 0 : cur.r.r.size;
      int rls = cur.r == null? 0 :
          cur.r.l == null? 0 : cur.r.l.size;

      if (rs < lls){
        // 右旋
        cur = rightRotate(cur);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(rs < lrs){
        // 左旋加右旋
        cur.l = leftRotate(cur.l);
        cur = rightRotate(cur);
        cur.l = maintain(cur.l);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }else if(ls < rrs){
        // 左旋
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur = maintain(cur);
      }else if(ls < rls){
        // 右旋加左旋
        cur.r = rightRotate(cur.r);
        cur = leftRotate(cur);
        cur.l = maintain(cur.l);
        cur.r = maintain(cur.r);
        cur = maintain(cur);
      }
      return cur;
    }


    public SBTNode<K> findLastKey(K key){
      if (null == key){
        return null;
      }

      SBTNode<K> cur = root;
      SBTNode<K> ans = root;

      while (cur != null) {
        ans = cur;
        if (cur.key.compareTo(key) == 0) {
          break;
        } else if (cur.key.compareTo(key) < 0) {
          cur = cur.r;
        } else if (cur.key.compareTo(key) > 0) {
          cur = cur.l;
        }

      }
      return ans;
    }

    public void add(K key) throws Exception {
      if (null == key){
        throw new Exception("invalid parameter");
      }

      SBTNode<K> lastKey = findLastKey(key);
      if(lastKey == null || lastKey.key.compareTo(key) != 0){
        root = add(root, key);
      }
    }

    private SBTNode<K> add(SBTNode<K> cur, K key) {
      if (null == cur){
        return new SBTNode<>(key);
      }
      cur.size++;
      if (cur.key.compareTo(key) > 0){
        cur = add(cur.l, key);
      }else {
        cur = add(cur.r, key);
      }

      return cur;
    }

    public SBTNode<K> delete(SBTNode<K> cur, K key) throws Exception {
      if (null == key){
        throw new Exception("invalid parameters");
      }
      cur.size--;
      if (key.compareTo(cur.key) < 0){
        cur = delete(cur.l, key);
      }else if(key.compareTo(cur.key) > 0){
        cur = delete(cur.r, key);
      } else {
        // ==
        SBTNode<K> l = cur.l;
        SBTNode<K> r = cur.r;
        if (l == null && r == null){
          return null;
        }else if(l == null && r != null){
          return r;
        }else if(l != null && r == null){
          return l;
        }else {
          SBTNode<K> pre = null;
          SBTNode<K> mostRightLeft = r;
          while (mostRightLeft.l != null){
            mostRightLeft.size--;
            pre = mostRightLeft;
            mostRightLeft = mostRightLeft.l;
          }

          if (pre != null){
            pre.r = mostRightLeft.r;
            mostRightLeft.r = cur.r;
          }

          mostRightLeft.l = cur.l;
          mostRightLeft.size = cur.size;

          cur = mostRightLeft;
        }
      }

      return cur;
    }

    public K getIndexKey(int index) throws Exception {
      if (index < 0 || index >= this.size()){
        throw new Exception("invalid parameters");
      }

      return getIndex(root, index + 1).key;
    }

    private SBTNode<K> getIndex(SBTNode<K> cur, int index) {
      int ls = cur.l == null? 0 : cur.l.size;
      int rs = cur.r == null? 0 : cur.r.size;

      if (index == ls + 1){
        return cur;
      }else if(index > ls + 1){
        return getIndex(cur.r, index - ls - 1);
      }else {
        return getIndex(cur.l, index);
      }
    }

    private boolean containsKey(K key) throws Exception {
      if (key == null){
       throw new Exception("invalid parameter.");
      }

      SBTNode<K> ans = findLastKey(key);
      return ans == null? false : ans.key.compareTo(key) == 0? true : false;
    }

    private void remove(K key) throws Exception {
      if (null == key){
        throw new Exception("invalid parameter.");
      }

      boolean containsKey = containsKey(key);
      if (containsKey){
        delete(root, key);
      }
    }

  }




}
