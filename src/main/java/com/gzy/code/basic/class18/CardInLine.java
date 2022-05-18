package com.gzy.code.basic.class18;

/**
 * description: CardInLine date: 2022/4/26 16:01
 *
 * @author: guizhenyu
 */
public class CardInLine {



  /**
   * 数组里面存放着牌数字
   * 每次只能从头或者尾部拿牌
   * 求获胜者的分数
   *
   * @param args
   */
  public static void main(String[] args) {
    int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
    System.out.println(win1(arr));
    System.out.println(win2(arr));
//    System.out.println(win3(arr));

  }

  private static int win2(int[] arr) {
    if (null == arr || arr.length == 0){
      return 0;
    }
    int len = arr.length;

    int[][] firstDp = new int[len][len];
    int[][] secondDp = new int[len][len];

    for (int i = 0; i < len; i++){
      firstDp[i][i] = arr[i];
//      secondDp[i][i] = 0;
    }

//    for (int l = 0; l < len; l++){
//      for (int r = l; r < len - 1; r++){
//        int score1 = arr[r] + secondDp[r + 1][r + 1];
//        int score2 = arr[r + 1] + secondDp[r][r];
//        firstDp[l][r] = Math.max(score1, score2);
//
//        int score3 = firstDp[r + 1][r + 1];
//        int score4 = firstDp[r][r];
//        secondDp[l][r] = Math.max(score3, score4);
//      }
//    }

    for (int i = 1; i < len; i++){
      int rowStart = 0;
      int colStart = i;

      while (colStart < len){
        firstDp[rowStart][colStart] = Math.max(arr[rowStart] + secondDp[rowStart+ 1][colStart],
            arr[colStart] + secondDp[rowStart][colStart - 1]);
        secondDp[rowStart][colStart] = Math.min(firstDp[rowStart + 1][colStart],
            firstDp[rowStart][colStart - 1]);
        colStart++;
        rowStart++;
      }
    }


    return Math.max(firstDp[0][len - 1], secondDp[0][len - 1]);

  }

  private static int win1(int[] arr) {
    if (null == arr || arr.length == 0){
      return 0;
    }

    int scoreFirstPick = firstPick(arr, 0, arr.length - 1);
    int scoreSecondPick = secondPick(arr, 0, arr.length - 1);
    return Math.max(scoreFirstPick, scoreSecondPick);

  }

  private static int secondPick(int[] arr, int l, int r) {

    if (l == r){
      return 0;
    }

    int score1 = firstPick(arr, l + 1, r);
    int score2 = firstPick(arr, l, r - 1);
    return Math.min(score1, score2);
  }

  private static int firstPick(int[] arr, int l, int r) {

    if (l == r){
      return arr[l];
    }

    int score1 = arr[l] + secondPick(arr, l + 1, r);
    int score2 = arr[r] + secondPick(arr, l, r - 1);
    return Math.max(score1, score2);
  }


}
