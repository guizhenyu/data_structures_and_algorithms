package com.gzy.code.basic.class18.again;

/**
 * description: CardInLine date: 2022/7/20 09:20
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

    int[] cards = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
    System.out.println(way1(cards));
    System.out.println(dp(cards));
    System.out.println(dp1(cards));

  }

  /**
   * 动态规划简化版，搜索记忆
   * @param cards
   * @return
   */
  private static int dp(int[] cards) {
    if (cards == null || cards.length == 0){
      return -1;
    }
    int N = cards.length;
    int[][] firstDp = new int[N][N];

    int[][] secondDp = new int[N][N];

    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        firstDp[i][j] = -1;
        secondDp[i][j] = -1;
        if (i == j){
          firstDp[i][j] = cards[i];
        }

      }
    }

    int first = firstDpPick(cards, 0, N - 1, firstDp, secondDp);
    int second = secondDpPick(cards, 0, N - 1, firstDp, secondDp);

    return Math.max(first, second);
  }


  private static int secondDpPick(int[] cards, int l, int r, int[][] firstDp, int[][] secondDp) {
    if (secondDp[l][r] != -1){
      return secondDp[l][r];
    }

    int ans = 0;
    if (l == r){
      ans = 0;
    }else {

      ans = Math.min(firstDpPick(cards, l + 1, r, firstDp, secondDp),
          firstDpPick(cards, l, r - 1, firstDp, secondDp));
    }

    secondDp[l][r] = ans;
    return ans;
  }

  private static int firstDpPick(int[] cards, int l, int r, int[][] firstDp, int[][] secondDp) {
    if (firstDp[l][r] != -1){
      return firstDp[l][r];
    }


    int ans = 0;
    if (l == r){
      ans = firstDp[l][r];
    }else {

      ans = Math.max(cards[l] + secondDpPick(cards, l + 1, r, firstDp, secondDp),
          cards[r] + secondDpPick(cards, l, r - 1, firstDp, secondDp));

    }

    firstDp[l][r] = ans;
    return ans;
  }

  /**
   * 动态规划，进阶版
   *
   * @param cards
   * @return
   */
  private static int dp1(int[] cards) {
    if (cards == null || cards.length == 0){
      return -1;
    }
    int N = cards.length;
    int[][] firstDp = new int[N][N];

    int[][] secondDp = new int[N][N];

    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        if (i == j){
          firstDp[i][j] = cards[i];
          secondDp[i][j] = 0;
        }
      }
    }
    // i 代表 总共要填几轮， 我们现在要填的是一个N * N的二维数组上的右半部分，不包括对称线上的，对称线上已经填好了
    // i 其实也就是 对称线往右能够平移几次并且确保能和这个二维数组相交
    for (int i = 0; i < N - 1; i++){


      for (int l = 0, r = i + 1; r < N; l++, r++){
        // 先填 firstDp
        firstDp[l][r] = Math.max(cards[l] + secondDp[l + 1][r],
            cards[r] + secondDp[l][r - 1]);
        // 再填 secondDp
        secondDp[l][r] = Math.min(firstDp[l + 1][r], firstDp[l][r - 1]);
      }

    }
    return Math.max(firstDp[0][N - 1], secondDp[0][N - 1]);
  }

  /**
   * 方法一：
   * 暴力递归的方法
   *     根据规则，抽象方法
   *
   * @param cards
   * @return
   */
  private static int way1(int[] cards) {
    if (cards == null || cards.length == 0){
      return -1;
    }
    // 每次只能从头或者尾部拿牌
    // 首选和第二次选两种情况，求两种的最大值
    int first = firstPick(cards, 0, cards.length - 1);
    int second = secondPick(cards, 0, cards.length - 1);
    return Math.max(first, second);
  }

  private static int firstPick(int[] cards, int l, int r) {
    // 结束条件是 左边界跟右边界相交
    if (l == r){
      //由于这边的规则是首选，所以他直接选了
      return cards[l];
    }

    int firstL = secondPick(cards, l + 1, r) + cards[l];
    int firstR = secondPick(cards, l, r - 1) + cards[r];
    return Math.max(firstL, firstR);

  }

  private static int secondPick(int[] cards, int l, int r) {
    if (l == r){
      return 0;
    }


    int secondR = firstPick(cards, l + 1, r);
    int secondL = firstPick(cards, l, r - 1);
    //todo  他要赢，这边所以要选最小值
    return Math.min(secondL, secondR);
  }


}
