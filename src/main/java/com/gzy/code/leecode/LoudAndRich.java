package com.gzy.code.leecode;

import java.util.Arrays;
import java.util.OptionalInt;

/**
 * description: LoudAndQich date: 2021/12/15 11:42 上午
 *
 * @author: guizhenyu
 */
public class LoudAndRich {

  public static void main(String[] args) {

    int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
    int[] quiet = {3,2,5,4,6,1,7,0};

    int[] result = loudAndRich(richer, quiet);
    System.out.println(result);
  }

  public static int[] loudAndRich(int[][] richer, int[] quiet){

    if (richer.length < 1){
      return new int[1];
    }

    int size = quiet.length;
    int[] result = new int[size];
    // 生成一个size * size的二维数组
    int[][] loudAndRichMatrix = new int[size][size];


    // 讲richer数组中的信息填到 loudAndRichMatrix中，
    // 如 [1，0]代表loudAndRichMatrix[1][0] = 1
    for (int j = 0; j < richer.length; j++){

      int[] ints = richer[j];
      int row = ints[0];
      int column = ints[1];

      // 有个传递的过程
      int[] loudAndRichMatrix1 = loudAndRichMatrix[column];
      loudAndRichMatrix[row] = loudAndRichMatrix1;
      loudAndRichMatrix[row][column] = quiet[row];
      loudAndRichMatrix[row][row] = quiet[row];
    }

    int[][] loudAndRichMatrix1 = new int[size][size];


    for (int j = 0; j < richer.length; j++){
      for (int i = 0; i< richer.length; i++){

        loudAndRichMatrix1[i][j] = loudAndRichMatrix1[j][i];
      }
    }

    for (int i = 0; i < size; i++) {

      int asInt = Arrays.stream(loudAndRichMatrix1[i]).max().getAsInt();
      for (int j = 0 ; j < size; j ++){
        if (asInt == quiet[j]){
          result[i] = j;
        }
      }

    }



    return result;
  }

}
