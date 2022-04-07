package com.gzy.code.basic.class15;

/**
 * description: FriendCircles date: 2022/4/2 8:07 上午
 *
 * 一个 N * N的二维数组， 坐标上的值为1，代表坐标x, y 是好有关系
 * 求朋友圈的个数
 *
 * @author: guizhenyu
 */
public class FriendCircles {

  static class UnionFind{
    // 记录父节点
    int[] parents;
    // 记录每个节点的做为父节点时，朋友的个数
    int[] sizes;
    // 朋友圈的个数
    int sets;
    // 辅助数组，用于跟新子节点的父节点
    int[] help;

    public UnionFind(int n){
      parents = new int[n];
      sizes = new int[n];
      sets = n;
      help = new int[n];
      for (int i = 0; i < n; i++){
        parents[i] = i;
        sizes[i] = 1;
      }
    }

    private  int find(int i) {
      int h = 0;
      while (i != parents[i]){
        help[h++] = i;
        i = parents[i];
      }

      for (h--; h >= 0; h--){
        parents[help[h]] = i;
      }
      return i;

    }
    public  void union(int i, int j){
      int find1 = find(i);
      int find2 = find(j);
      if (find1 != find2){
        if (sizes[find1] >= sizes[find2]){
          sizes[find1] += sizes[find2];
          parents[find2] = find1;

        }else {
          sizes[find2] += sizes[find1];
          parents[find1] = find2;

        }
        sets--;
      }

    }

    public int sets() {
      return sets;
    }

  }


  public static void main(String[] args) {
    int maxSize = 100;
    int testSize = 10000;

    for (int i = 0; i < testSize; i ++){
      int[][] relationshipArr = generateArr(maxSize);

      if (friendCircle(relationshipArr) != unionFriendCircle(relationshipArr)){
        System.out.println("oops");
        return;
      }
    }

    System.out.println("finish");
  }

  /**
   * 并查集解决
   *
   * @param relationshipArr
   * @return
   */
  private static int unionFriendCircle(int[][] relationshipArr) {
    if (relationshipArr == null || relationshipArr.length == 0){
      return 0;
    }
    int size = relationshipArr.length;
    UnionFind unionFind = new UnionFind(size);

    for (int i = 0; i < size; i++){
      for (int j = i + 1; j < size; j++){
        if (relationshipArr[i][j] == 1){
          unionFind.union(i, j);
        }
      }
    }

    return unionFind.sets;
  }

  /**
   * 暴力方式解决
   *
   * @param relationshipArr
   * @return
   */
  private static int friendCircle(int[][] relationshipArr) {

    return 0;
  }

  /**
   * 随机生成关系数组
   *
   * @param maxSize
   * @return
   */
  private static int[][] generateArr(int maxSize) {

    int size = (int)Math.random() * maxSize;

    int[][] arr = new int[size][size];

    for (int i = 0; i < size; i++){
      for (int j = 0; j < size; j++){
        arr[i][j] = Math.random() > 0.5? 1 : 0;
      }
    }

    return arr;
  }


}
