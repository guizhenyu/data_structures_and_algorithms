package com.gzy.code.basic.class22;

/**
 * description: KillMonster date: 2022/8/5 17:10
 *  给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 * @author: guizhenyu
 */
public class KillMonster {


  public static void main(String[] args) {
    int maxN = 10;
    int maxM = 10;
    int maxK = 10;
    int testTime = 100000;

    System.out.println("test start");

    for (int i = 0; i < testTime; i++){

      int N = (int)(Math.random() * maxN);
      int M = (int)(Math.random() * maxM);
      int K = (int)(Math.random() * maxK);

      double ans1 = violenceRecursive(N, M, K);
      double ans2 = dp1(N, M, K);
      double ans3 = dp2(N, M, K);

      if (ans1 != ans2 || ans2 != ans3){
        System.out.println("Oops!");
        return;
      }
    }

    System.out.println("test success!");
  }

  private static double dp2(int n, int m, int k) {

    if (n < 1 || m < 1 || k < 1){
      return 0;
    }

    long all = (long)Math.pow(m + 1, k);
    long[][] dp = new long[n + 1][k + 1];

    for (int i = 0; i <= k; i++){
      dp[0][i] = (long)(Math.pow(m + 1, i));
    }

    for (int rest = 1; rest <= k; rest++){
      for (int restBlood = 1; restBlood <= n; restBlood++){
        long ans = dp[restBlood][rest - 1] + dp[restBlood - 1][rest];
//        if (restBlood <= m){
//          ans += (long)(Math.pow(m + 1, rest - 1)) * (restBlood - m + 1);
//        }

//        ans += dp[restBlood - 1][rest];

        if (restBlood - 1 - m >= 0){
          ans -= dp[restBlood - 1- m][rest - 1];
        }else {
          ans -= (long)(Math.pow(m + 1, rest - 1));
        }
        dp[restBlood][rest] = ans;

//        for (int hurt = 0; hurt <= m; hurt++){
//          if (restBlood - hurt <= 0){
//            ans += (long)(Math.pow(m + 1, rest - 1));
//          }else {
//            ans += dp[restBlood - hurt][rest - 1];
//          }
//          dp[restBlood][rest] = ans;
//        }
      }
    }

    return (double) (dp[n][k] / all);
  }

  private static double dp1(int n, int m, int k) {
    if (n < 1 || m < 1 || k < 1){
      return 0;
    }

    long all = (long)Math.pow(m + 1, k);
    long[][] dp = new long[n + 1][k + 1];

    for (int i = 0; i <= k; i++){
      dp[0][i] = (long)(Math.pow(m + 1, i));
    }

    for (int rest = 1; rest <= k; rest++){
      for (int restBlood = 1; restBlood <= n; restBlood++){
        int ans = 0;
        for (int hurt = 0; hurt <= m; hurt++){
          if (restBlood - hurt <= 0){
            ans += (long)(Math.pow(m + 1, rest - 1));
          }else {
            ans += dp[restBlood - hurt][rest - 1];
          }
          dp[restBlood][rest] = ans;
        }
      }
    }

    return (double) (dp[n][k] / all);
  }

  private static double violenceRecursive(int n, int m, int k) {
    if (n < 1 || m < 1 || k < 1){
      return 0;
    }

    long all = (long)Math.pow(m + 1, k);

    return (double) (process(n, m, k) / all);

  }

  private static long process(int n, int m, int k) {
    if (k == 0){
      return n <= 0? 1 : 0;
    }

    if (n <= 0){
      // 怪兽此时已经死了，所以剩余的攻击数，可以随便砍
      return (long)(Math.pow(m + 1, k));
    }

    int ans = 0;
    for (int blood = 0; blood <= m; blood++){
      ans += process(n - blood, m, k - 1);
    }


    return ans;
  }


}
