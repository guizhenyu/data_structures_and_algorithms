package com.gzy.code.basic.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * description: NumberOfIslands_20220702 date: 2022/7/2 12:21
 *
 * @author: guizhenyu
 */
public class NumberOfIslands_20220702 {

  /**
   * 条件：
   * 1. 一个二维数组，N * M ,代表一片水域
   * 2. 每个点只有 0 和 1两个值， 0代表是水， 1代表是岛屿
   * 3. 求这个片水域，总共有多少个岛屿
   */

  public static void main(String[] args) {
    int testTime = 100000;
    int maxWidth = 500;
    int maxHigh = 500;

    for (int i = 0; i < testTime; i++){
      int[][] area = generateRandomArea(maxWidth, maxHigh);
      int[][] area1 = copyArea(area);
      int[][] area2 = copyArea(area);

      int ans1 = infect(area);
      int ans2 = unionMapFuc(area1);
      int ans3 = unionArrFuc(area2);

      if (ans1 != ans2){
        System.out.println("sdfg");
      }
    }

  }

  private static int unionArrFuc(int[][] area) {
    if (null == area || area.length == 0){
      return 0;
    }

    UnionArr unionArr = new UnionArr(area);
    int row = area.length;
    int col = area[0].length;

    for (int i = 1; i < row; i++){
      if (area[i][0] == 1 && area[i - 1][0] == 1){
        unionArr.union(i, 0, i - 1, 0);
      }
    }

    for (int j = 1; j < col; j++){
      if (area[0][j] == 1 && area[0][j - 1] == 1){
        unionArr.union(0,j, 0,j - 1);
      }
    }

    for (int r = 1; r < row; r++){
      for (int c = 1; c < col; c++){
        if (area[r][c] == 1){
          if (area[r - 1][c] == 1){
            unionArr.union(r, c, r - 1, c);
          }
          if (area[r][c - 1] == 1){
            unionArr.union(r, c, r,c - 1);
          }
        }
      }
    }

    return unionArr.size;

  }


  public static class UnionArr{
    public int[] parents;
    public int[] sizeArr;
    public int[] helpArr;
    public int size;
    public int col;

    public UnionArr(int[][] area){
      int row = area.length;
      col = area[0].length;
      parents = new int[row * col];
      helpArr = new int[row * col];
      sizeArr = new int[row * col];
      for (int r = 0; r < row; r++){
        for (int c = 0; c < col; c++ ){
          if (area[r][c] == 1){
            int index = indexCount(r, c);
            sizeArr[index] = 1;
            parents[index] = index;
            size++;
          }
        }
      }
    }

    public int indexCount(int row, int c){
      return row * col + c;
    }
    public int findFather(int row, int col){
      int index = indexCount(row, col);
      int helpIndex = 0;
      while (index != parents[index]){
        helpArr[helpIndex++] = index;
        index = parents[index];
      }

      for (helpIndex--; helpIndex >= 0; helpIndex--){
        parents[helpArr[helpIndex]] = index;
      }

      return index;
    }

    public void union(int row, int col, int row1, int col1){
      int fatherA = findFather(row, col);
      int fatherB = findFather(row1, col1);

      if (fatherA != fatherB){
        int sizeA = sizeArr[fatherA];
        int sizeB = sizeArr[fatherB];

        int big = sizeA >= sizeB? fatherA : fatherB;
        int small = sizeA >= sizeB? fatherB : fatherA;

        parents[small] = big;
        sizeArr[big] = sizeA + sizeB;
        size--;
      }



    }

  }

  private static int unionMapFuc(int[][] area) {
    if (null == area || area.length == 0){
      return 0;
    }
    Dot[][] dotArr = new Dot[area.length][area[0].length];
    List<Dot> dots = new ArrayList<>();
    for (int i = 0; i < area.length; i++){
      for (int j = 0; j < area[0].length; j++){
        if (area[i][j] == 1){
          dotArr[i][j] = new Dot();
          dots.add(dotArr[i][j]);
        }

      }
    }

    UnionMap<Dot> dotUnionMap = new UnionMap<>(dots);
    for (int h = 1; h < area.length; h++){
      if (area[h][0] == 1 && area[h - 1][0] == 1){
        dotUnionMap.union(dotArr[h][0], dotArr[h - 1][0]);
      }
    }

    for (int w = 1; w < area[0].length; w++){
      if (area[0][w] == 1 && area[0][w - 1] == 1){
        dotUnionMap.union(dotArr[0][w], dotArr[0][w - 1]);
      }
    }

    for (int h = 1; h < area.length; h++){
      for (int w = 1; w < area[0].length; w++){
        if (area[h][w] == 1){
          if (area[h - 1][w] == 1){
            dotUnionMap.union(dotArr[h][w], dotArr[h - 1][w]);
          }
          if (area[h][w - 1] == 1){
            dotUnionMap.union(dotArr[h][w], dotArr[h][w - 1]);
          }

        }
      }
    }



    return dotUnionMap.sizeMap.size();
  }

  public static class Dot{}

  private static int infect(int[][] area) {
    if (null == area || area.length == 0){
      return 0;
    }

    int ans = 0;
    for (int h = 0; h < area.length; h++){
      for (int w = 0; w < area[0].length; w++){
        if (area[h][w] == 1){
          ans++;
          process(area, h, w);
        }
      }
    }

    return ans;
  }

  private static void process(int[][] area, int h, int w) {
    if (h < 0 || h == area.length || w < 0 || w == area[0].length || area[h][w] == 0){
      return;
    }
    area[h][w] = 0;
    process(area, h - 1, w);
    process(area, h + 1, w);
    process(area, h, w - 1);
    process(area, h, w + 1);
  }

  private static int[][] generateRandomArea(int maxWidth, int maxHigh) {

    int width = (int)(Math.random() * maxWidth) + 1;
    int high = (int)(Math.random() * maxHigh) + 1;
    int[][] area = new int[high][width];

    for (int h = 0; h < high; h++){
      for (int w = 0; w < width; w++){
        area[h][w] = Math.random() > 0.5? 1 : 0;
      }
    }
    return area;
  }


  public static int[][] copyArea(int[][] area){
    if (area == null || area.length == 0){
      return null;
    }

    int[][] ans = new int[area.length][area[0].length];
    for (int h = 0; h < area.length; h++){
      for (int w = 0; w < area[0].length; w++){
        ans[h][w] = area[h][w];

      }
    }
    return ans;

  }

  /**
   * map实现并查集
   */
  public static class UnionMap<V>{

    private Map<V, Node<V>> nodeMap;

    private Map<Node<V>, Node<V>> parentMap;

    private Map<Node<V>, Integer> sizeMap;

    private Set<Node> sets;

    public UnionMap(List<V> values){
      nodeMap = new HashMap<>(values.size());
      parentMap = new HashMap<>(values.size());
      sizeMap = new HashMap<>(values.size());
      sets = new HashSet<>(values.size());

      for (int i = 0; i < values.size(); i++){
        Node<V> node = new Node<>(values.get(i));
        nodeMap.put(values.get(i), node);
        parentMap.put(node, node);
        sizeMap.put(node, 1);
      }
    }


    public Node<V> findFather(Node<V> node){
      Node<V> cur = node;
      Stack<Node<V>> stack = new Stack<Node<V>>();
      while (cur != parentMap.get(cur)){
        stack.push(cur);
        cur = parentMap.get(cur);
      }

      while (!stack.isEmpty()){
        parentMap.put(stack.pop(), cur);
      }

      return cur;
    }

    public void union(V valueA, V valueB){
      Node<V> nodeA = nodeMap.get(valueA);
      Node<V> nodeB = nodeMap.get(valueB);
      Node<V> fatherA = findFather(nodeA);
      Node<V> fatherB = findFather(nodeB);
      if (fatherA != fatherB){
        int sizeA = sizeMap.get(fatherA);
        int sizeB = sizeMap.get(fatherB);

        Node<V> big = sizeA >= sizeB? fatherA : fatherB;
        Node<V> small = sizeA >= sizeB? fatherB : fatherA;

        parentMap.put(small, big);
        sizeMap.put(big, sizeA + sizeB);
        sizeMap.remove(small);
      }

    }

  }

  public static class Node<V>{
    private V value;

    public Node(V v){
      value = v;
    }
  }
}
