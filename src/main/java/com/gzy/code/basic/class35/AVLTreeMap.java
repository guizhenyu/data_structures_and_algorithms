package com.gzy.code.basic.class35;

/**
 * description: AVLTreeMap date: 2022/5/17 19:46
 *
 * @author: guizhenyu
 */
public class AVLTreeMap {

  public static class AVLNode<K extends Comparable<K>, V>{
    K k;
    V v;

    AVLNode<K, V> l;

    AVLNode<K, V> r;


    int h;

    public AVLNode(K key, V value){
      k = key;
      v = value;
    }
  }

  public static class AVLTree<K extends Comparable<K>, V>{
    AVLNode<K, V> root;
    int size;

    public AVLTree(){
      root = null;
      size = 0;

    }

    public AVLNode<K, V> leftRotate(AVLNode<K, V> cur){
      AVLNode<K, V> right = cur.r;
      cur.r = right.l;
      right.l = cur;
      cur.h = Math.max(cur.l == null ? 0 : cur.l.h,
          cur.r == null ? 0 : cur.r.h) + 1;
      right.h = Math.max(right.r == null ? 0 : right.r.h,
          cur.h) + 1;
      return right;
    }

    public AVLNode<K, V> rightRotate(AVLNode<K, V> cur){
      AVLNode<K, V> left = cur.l;
      cur.l = left.r;
      left.r = cur;
      cur.h = Math.max(cur.l == null? 0 : cur.l.h, cur.r == null? 0 : cur.r.h) + 1;
      left.h = Math.max(cur.h, left.l == null? 0 : left.l.h) + 1;

      return left;

    }

    public AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value){
      if (null == cur){
        return new AVLNode<>(key, value);
      }

      if (cur.k.compareTo(key) > 0){
        cur.l = add(cur.l, key, value);
      }else {
        cur.r = add(cur.r, key, value);
      }
      cur.h = Math.max(cur.l == null? 0 : cur.l.h,
          cur.r == null? 0 : cur.r.h) + 1;
      return maintain(cur);
    }

    public AVLNode<K, V> delete(AVLNode<K, V> cur, K key){
      if (cur == null){
        return null;
      }

      if (cur.k.compareTo(key) == 0){
        // 删除当前节点
        // 1. 当前节点没有子节点, 置空当前节点
        if (cur.l == null && cur.r == null){
          cur = null;
        }
        // 2. 只有左节点, 左节点直接替换当前节点
        else if (cur.l != null && cur.r == null){
          cur = cur.l;
        }
        // 3. 只有右节点, 右节点直接替换当前节点
        else if(cur.l == null && cur.r != null){
          cur = cur.r;
        }
        // 4. 左右节点都有, 只需要将右节点的最左节点替换当前的节点
        else{
          AVLNode<K, V> des = cur.r;
          while (des != null){
            des = des.l;
          }
          // todo : 这边还得先删除 des节点，因为des可能还有右节点
          cur.r = delete(cur.r, des.k);
          des.l = cur.l;
          des.r = cur.r;
          cur = des;
        }
      }else if (cur.k.compareTo(key) < 0){
        cur.r = delete(cur.r, key);
      }else {
        cur.l = delete(cur.l, key);
      }

      if (cur != null){
        cur.h = Math.max(cur.l == null? 0 : cur.l.h, cur.r == null? 0 : cur.r.h) + 1;
      }
      return maintain(cur);
    }

    public AVLNode<K, V> maintain(AVLNode<K, V> cur){
      if (cur == null){
        return null;
      }

      int lh = cur.l == null? 0 : cur.l.h;
      int rh = cur.r == null? 0 : cur.r.h;
      if (Math.abs(lh - rh) < 2 ){
        //高度差满足平衡的规则
        return cur;
      }

      if (lh > rh){
        // 左节点高
        int llh = cur.l.l == null? 0 : cur.l.l.h;
        int lrh = cur.l.r == null? 0 : cur.l.r.h;
        // 1. 是左节点左节点高
        if (llh > lrh){
          // cur 节点右旋转
          cur = rightRotate(cur);
        }
        // 2. 左节点的右节点高
        else if(llh < lrh){
          // 左节点先左转，然后cur节点右转
          cur.l = leftRotate(cur.l);
          cur = rightRotate(cur);
        }
        // 3. 两边都高
        else {
          // 只需要cur节点右转， 不需要管左节点的右节点
          cur = rightRotate(cur);
        }
      }else{
        // 右节点高
        int rlh = cur.r.l == null? 0 : cur.r.l.h;
        int rrh = cur.r.r == null? 0 : cur.r.r.h;
        if (rlh > rrh){
          cur.r = rightRotate(cur.r);
          cur = leftRotate(cur);
        }else {
          cur = leftRotate(cur);
        }
      }
      return cur;
    }

  }

}
