package com.gzy.code.basic.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * description: NumberOfIslands date: 2022/4/2 2:18 下午
 *
 * @author: guizhenyu
 */
public class NumberOfIslands {


  public static void main(String[] args) {
    int row = 0;
    int col = 0;
    char[][] board1 = null;
    char[][] board2 = null;
    char[][] board3 = null;
    long start = 0;
    long end = 0;

    row = 1000;
    col = 1000;
    board1 = generateRandomMatrix(row, col);
    board2 = copy(board1);
    board3 = copy(board1);
    System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
    System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

    start = System.currentTimeMillis();
    System.out.println("感染方法的运行结果: " + numIslands1(board1));
    end = System.currentTimeMillis();
    System.out.println("感染方法的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行结果: " + numIslands2(board2));
    end = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行结果: " + numIslands3(board3));
    end = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

    System.out.println();

    row = 10000;
    col = 10000;
    board1 = generateRandomMatrix(row, col);
    board3 = copy(board1);
    board2 = copy(board1);
    System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
    System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

    start = System.currentTimeMillis();
    System.out.println("感染方法的运行结果: " + numIslands3(board1));
    end = System.currentTimeMillis();
    System.out.println("感染方法的运行时间: " + (end - start) + " ms");

    start = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行结果: " + numIslands1(board2));
    end = System.currentTimeMillis();
    System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");
    start = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行结果: " + numIslands2(board3));
    end = System.currentTimeMillis();
    System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

  }

  private static int numIslands1(char[][] board) {

    int islands = 0;
    int row = board.length;
    int col = board[0].length;
    for (int i = 0; i < row; i++){
      for (int j = 0; j < col; j++){
        if (board[i][j] == '1'){
          islands++;
          infect(board, i, j);
        }
      }
    }

    return islands;
  }

  private static void infect(char[][] board, int i, int j) {
    if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1'){
     return;
    }
    board[i][j] = 0;
    infect(board, i - 1, j);
    infect(board, i + 1, j);
    infect(board, i , j - 1);
    infect(board, i , j + 1);

  }

  private static int numIslands2(char[][] board) {

    int row = board.length;
    int col = board[0].length;
    Dot[][] dotArr = new Dot[row][col];
    List<Dot> dotList = new ArrayList<>();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++){
        if (board[i][j] == '1'){
          dotArr[i][j] = new Dot();
          dotList.add(dotArr[i][j]);
        }
      }
    }

    UnionMap<Dot> dotUnionMap = new UnionMap<>(dotList);

    for (int i = 1; i < row; i++){
      if (board[i][0] == '1' && board[i - 1][0] == '1'){
        dotUnionMap.union(dotArr[i][0], dotArr[i - 1][0]);
      }
    }

    for(int j = 1; j < col; j++){
      if (board[0][j - 1] == '1' && board[0][j] == '1'){
        dotUnionMap.union(dotArr[0][j - 1], dotArr[0][j]);
      }
    }

    for (int i = 1; i < row; i++){
      for (int j = 1; j < col; j++){
        if (board[i][j] == '1'){
          if (board[i - 1][j] == '1'){
            dotUnionMap.union(dotArr[i][j], dotArr[i - 1][j]);
          }
          if (board[i][j - 1] == '1'){
            dotUnionMap.union(dotArr[i][j], dotArr[i][j - 1]);
          }
        }
      }
    }

    return dotUnionMap.getSize();

  }

  private static int numIslands3(char[][] board) {
    int row = board.length;
    int col = board[0].length;
    UnionArr union = new UnionArr(board);

    for (int i = 1; i < row; i++){
      if (board[i - 1][0] == '1' && board[i][0] == '1'){
        union.union(i - 1, 0, i, 0);
      }
    }

    for (int j = 1; j < col; j++){
      if (board[0][j - 1] == '1' && board[0][j] == '1'){
        union.union(0, j - 1, 0, j);
      }
    }


    for (int i = 1; i < row; i++){
      for (int j = 1; j < col; j++){
        if (board[i][j] == '1'){
          if (board[i - 1][j] == '1'){
            union.union(i, j, i - 1, j);
          }
          if (board[i][j - 1] == '1'){
            union.union(i, j, i, j - 1);
          }
        }
      }
    }

    return union.sets();
  }





  // 为了测试
  public static char[][] generateRandomMatrix(int row, int col) {
    char[][] board = new char[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        board[i][j] = Math.random() < 0.5 ? '1' : '0';
      }
    }
    return board;
  }

  // 为了测试
  public static char[][] copy(char[][] board) {
    int row = board.length;
    int col = board[0].length;
    char[][] ans = new char[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        ans[i][j] = board[i][j];
      }
    }
    return ans;
  }


}
class Node<V>{
  V v;

  public Node(V v){
    this.v = v;
  }
}

class Dot{}

class UnionMap<V>{
  // V 是一个泛型，表示 board[i][j]是1
  // Node<V> 表示这个节点
  Map<V, Node<V>> nodeMap = new HashMap<>();
  // 大小
  Map<Node<V>, Node<V>> parentMap = new HashMap<>();
  // 大小
  Map<Node<V>, Integer> sizeMap = new HashMap<>();

  public UnionMap(List<V> vList){
    int len = vList.size();
    for (int i = 0; i < len; i++){
      V v = vList.get(i);
      Node<V> vNode = new Node<>(v);
      nodeMap.put(v, vNode);
      parentMap.put(vNode, vNode);
      sizeMap.put(vNode, 1);
    }
  }

  public Node<V> find(Node<V> node){
    Stack<Node> stack = new Stack<>();
    while (node != parentMap.get(node)){
      stack.push(node);
      node = parentMap.get(node);
    }

    while (!stack.isEmpty()){
      parentMap.put(stack.pop(), node);
    }
    return node;
  }

  public void union(V a, V b){

    Node<V> aNode = nodeMap.get(a);
    Node<V> bNode = nodeMap.get(b);
    Node<V> aParentNode = find(aNode);
    Node<V> bParentNode = find(bNode);
    if (aParentNode != bParentNode){
      if (sizeMap.get(aParentNode) >= sizeMap.get(bParentNode)){
        parentMap.put(bParentNode, aParentNode);
        sizeMap.put(aParentNode, sizeMap.get(aParentNode) + sizeMap.get(bParentNode));
        sizeMap.remove(bParentNode);
      }else {
        parentMap.put(aParentNode, bParentNode);
        sizeMap.put(bParentNode, sizeMap.get(aParentNode) + sizeMap.get(bParentNode));
        sizeMap.remove(aParentNode);
      }
    }
  }


  public int getSize(){
    return sizeMap.size();
  }
}

class UnionArr{

  int[] parent;
  int[] size;
  int[] help;
  int sets;
  int col;

  public UnionArr(char[][] arr){

    int row = arr.length;
    col = arr[0].length;
    int len = row * col;
    parent = new int[len];
    size = new int[len];
    help = new int[len];
    for(int i = 0; i < row; i++){
      for (int j = 0; j < col; j++){
        if (arr[i][j] == '1'){
          int index = index(i, j);
          parent[index] = index;
          size[index] = 1;
          sets++;
        }

      }

    }
  }

  public int index(int row, int index){
    return row * col + index;
  }

  public int findFather(int row, int col){
    int index = index(row, col);
    int hi = 0;
    while (index != parent[index]){
      help[hi++] = index;
      index = parent[index];
    }

    for (hi-- ; hi >= 0; hi--){
      parent[help[hi]] = index;
    }

    return index;
  }

  public void union(int row, int col, int row1, int col1){

    int f1 = findFather(row, col);
    int f2 = findFather(row1, col1);
    if (f1 != f2){
      if (size[f1] >= size[f2]){
        size[f1] += size[f2];
        parent[f2] = f1;
      }else{
        size[f2] += size[f1];
        parent[f1] = f2;
      }

      sets--;
    }


  }




  public int sets(){
    return sets;
  }

}