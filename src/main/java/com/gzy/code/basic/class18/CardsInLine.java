package com.gzy.code.basic.class18;

/**
 * description: CardsInLine date: 2022/4/9 1:37 下午
 *
 * @author: guizhenyu
 */
public class CardsInLine {


  /**
   * 数组里面存放着牌数字
   * 每次只能从头或者尾部拿牌
   * 求获胜者的分数
   *
   * @param args
   */
  public static void main(String[] args) {
//    int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
//    System.out.println(win1(arr));
//    System.out.println(win2(arr));
//    System.out.println(win3(arr));

//    13120903901
    for(int i = 1; i < 51; i++){
      String str = "131209039";
      if (i < 10){
        str += "0";
      }
      System.out.println("- " + str + i);
    }

  }

  private static int win1(int[] arr) {
    if (null == arr || arr.length == 0){
      return 0;
    }

    // 首先选，后面选
    int firstPick = firstPick(arr, 0, arr.length - 1);
    int secondPick = secondPick(arr, 0, arr.length - 1);
    return Math.max(firstPick, secondPick);
  }


  private static int firstPick(int[] arr, int l, int r) {
    if (l == r){
      return arr[l];
    }

    int ans1 = arr[l] + secondPick(arr, l + 1, r);
    int ans2 = arr[r] + secondPick(arr, l, r - 1);

    return Math.max(ans1, ans2);

  }


  private static int secondPick(int[] arr, int l, int r) {
    if (l == r){
      return 0;
    }

    int ans1 = firstPick(arr, l + 1, r);
    int ans2 = firstPick(arr, l, r - 1);

    return Math.min(ans1, ans2);

  }
  private static int win2(int[] arr) {
    
    if (null == arr || arr.length == 0){
      return 0;
    }
    int len = arr.length;

    int[][] pickFirstDp = new int[len][len];
    int[][] pickSecondDp = new int[len][len];

    for (int i = 0; i < len; i++) {
      for (int j = 0 ; j < len; j++){
        pickFirstDp[i][j] = -1;
        pickSecondDp[i][j] = -1;
      }
    }

    int firstPick = firstPick1(arr, 0, len - 1, pickFirstDp, pickSecondDp);
    int secondPick = secondPick1(arr, 0, len - 1, pickFirstDp, pickSecondDp);
    return Math.max(firstPick, secondPick); 
  }

  private static int secondPick1(int[] arr, int l, int r, int[][] pickFirstDp, int[][] pickSecondDp) {

    if (pickSecondDp[l][r] != -1){
      return pickSecondDp[l][r];
    }
    if (l == r){
      pickSecondDp[l][r] = 0;
      return 0;
    }

    int ans1 = firstPick1(arr, l + 1, r, pickFirstDp, pickSecondDp);
    int ans2 = firstPick1(arr, l, r - 1, pickFirstDp, pickSecondDp);
    pickSecondDp[l][r] = Math.min(ans1, ans2);

    return pickSecondDp[l][r];
  }

  private static int firstPick1(int[] arr, int l, int r, int[][] pickFirstDp, int[][] pickSecondDp) {
    if (pickFirstDp[l][r] != -1){
      return pickFirstDp[l][r];
    }
    if (l == r){
      pickFirstDp[l][r] = arr[l];
      return arr[l];
    }

    int ans1 = arr[l] + secondPick1(arr, l + 1, r, pickFirstDp, pickSecondDp);
    int ans2 = arr[r] + secondPick1(arr, l, r - 1, pickFirstDp, pickSecondDp);
    pickFirstDp[l][r] = Math.max(ans1, ans2);
    return pickFirstDp[l][r];
  }

  private static int win3(int[] arr) {
    if (null == arr || arr.length == 0){
      return 0;
    }
    int len = arr.length;

    int[][] pickFirstDp = new int[len][len];
    int[][] pickSecondDp = new int[len][len];

    for (int i = 0; i < len; i++) {
      pickFirstDp[i][i] = arr[i];
      pickSecondDp[i][i] = 0;
    }

    for (int i = 1; i < len; i++) {

      int rowStart = 0;
      int colStart = i;
      while (colStart < len){
        pickFirstDp[rowStart][colStart] = Math.max(arr[rowStart] + pickSecondDp[rowStart + 1][colStart] ,
            arr[colStart] +pickSecondDp[rowStart][colStart - 1]);
        pickSecondDp[rowStart][colStart] = Math.min(pickFirstDp[rowStart + 1][colStart],
            pickFirstDp[rowStart][colStart - 1]);
        colStart++;
        rowStart++;

      }
    }


    return Math.max(pickFirstDp[0][len - 1], pickSecondDp[0][len - 1]);
  }

}
