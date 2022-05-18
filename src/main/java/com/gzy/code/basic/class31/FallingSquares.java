package com.gzy.code.basic.class31;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * description: FallingSquares date: 2022/5/12 16:00
 *
 * @author: guizhenyu
 */
public class FallingSquares {
  /**
   * 想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
   * 下面是这个游戏的简化版：
   * 1）只会下落正方形积木
   * 2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
   * 3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
   * 4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。
   *
   * 给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
   * 返回每一次掉落之后的最大高度
   *
   *
   */



  public static class SegmentTree{

    // 线段树的长度
    int N;

    // 保存每个节点的最大高度
    int[] max;

    // 保存缓存的高度变化
    int[] change;

    // 记录change值是否有效
    boolean[] update;


    public SegmentTree(int n){
      N = n;
      max = new int[N << 2];
      change = new int[N << 2];
      update = new boolean[N << 2];
    }

    public int query(int L, int R, int l, int r,int rt){
      if (L <= l &&  r <= R){
        return max[rt];
      }

      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);

      int left = 0;
      int right = 0;

      if (L <= mid){
        left = query(L, R, l, mid, rt << 1);
      }
      if (R > mid){
        right = query(L, R, mid + 1, r, rt << 1 | 1);
      }

      return Math.max(left, right);


    }

    private void pushDown(int rt, int lLen, int rLen) {

      if (update[rt]){
        update[rt << 1] = true;
        update[rt << 1 | 1] = true;
        change[rt << 1] = change[rt];
        change[rt << 1 | 1] = change[rt];
        max[rt << 1] = change[rt];
        max[rt << 1 | 1] = change[rt];
        update[rt] = false;


      }

    }
    private void update(int L, int R, int C, int l, int r,int rt){
      if (L <= l && r <= R){
        change[rt] = C;
        update[rt] = true;
        max[rt] = C;

        return;
      }

      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);
      if (L <= mid){
        update(L, R, l,C,  mid, rt >> 1);
      }
      if (R > mid){
        update(L, R, C, mid + 1, r,rt >> 1 | 1);
      }

      pushUp(rt);

    }

    private void pushUp(int rt) {
      max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
    }

  }

  /**
   * int[][] ---》 （a, b） a代表以x = a 的轴线贴边落下， b代表正方形的宽度
   * @param fallingSquares
   * @return
   */
  public static Map<Integer, Integer> fallingSquaresToSegmentTreeIndex(int[][] fallingSquares){
    // 保存
    TreeSet<Integer> treeSet = new TreeSet<Integer>();

    for (int i = 0; i < fallingSquares.length; i++){
      int[] fallingSquare = fallingSquares[0];
      treeSet.add(fallingSquare[0]); // left
      treeSet.add(fallingSquare[0] + fallingSquare[1] - 1);// right
    }
    Map<Integer, Integer> map = new HashMap<>();
    int segmentTreeIndex = 0;
    for (Integer xIndex: treeSet){
      map.put(xIndex, ++segmentTreeIndex);
    }
    return map;
  }


  public static List<Integer> fallingSquares(int[][] fallingSquares){
    Map<Integer, Integer> indexMap = fallingSquaresToSegmentTreeIndex(fallingSquares);

    int size = indexMap.size();

    SegmentTree segmentTree = new SegmentTree(size + 1);

    int max = 0;
    List<Integer> res = new ArrayList<>();

    for (int[] fallingSquare : fallingSquares){
      int l = fallingSquare[0];
      int r = l + fallingSquare[1] - 1;
      int squareHeight = fallingSquare[1];

      int height = segmentTree.query(l, r, 1, size, 1) + squareHeight;
      max = Math.max(max, height);
      segmentTree.update(l, r, height,1, size, 1);
      res.add(max);
    }

    return res;
  }



}


