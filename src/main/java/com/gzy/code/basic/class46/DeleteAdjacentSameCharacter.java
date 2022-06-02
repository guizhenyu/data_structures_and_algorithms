package com.gzy.code.basic.class46;

/**
 * description: DeleteAdjacentSameCharacter date: 2022/6/1 10:55
 *
 * @author: guizhenyu
 */
public class DeleteAdjacentSameCharacter {

  public static void main(String[] args) {
    int maxLen = 16;
    int variety = 3;
    int testTime = 100000;
    
    for (int i = 0; i < testTime; i++){
      int len = (int)(Math.random() * maxLen) + 1;
      String str = generateArray(len, variety);
      int ans1 = natureWisdom(str);
      int ans2 = natureWisdom2(str);
      int ans = restMin3(str);
      if (ans != ans1 || ans2 != ans1){
        System.out.println("oops!");
      }

    }
  }

  private static int natureWisdom2(String str) {
    if (str == null || str.length() == 0){
      return 0;
    }

    if (str.length() < 2){
      return 1;
    }

    return process(str.toCharArray(), 0, str.length() - 1, false);

  }

  /**
   * 删除 L~R上面的数
   *
   * @param arr
   * @param L
   * @param R
   * @param has L 之前是否有跟L相等的数
   * @return
   */
  private static int process(char[] arr, int L, int R, boolean has) {

    if(L > R){
      return 0;
    }

    if (L == R){
      // 最好一个数，如果前面的数相等，就会直接消消乐了
      return has? 0 : 1;
    }

    int K = has? 1 : 0;
    int index = L;
    while (index <= R && arr[index] == arr[L]){
      K++;
      index++;
    }

    // 先尝试把能消除的清掉
    int ans = (K > 1 ? 0 : 1) + process(arr, index, R, false);
    int result = Integer.MAX_VALUE;
    // 这边是尝试继续看看后面有没有相等，就留到后面一起消除
    for (int split = index + 1 ; split <= R; split++){
      if (arr[split] == arr[L] &&
          /* 寻找到第index后跟L相等的数但是不是连续的，因为连续的会进下一个递归函数进行合并
          * 后面可能会有多个  L：a    bcaaaadaaa*/
      arr[split] != arr[split - 1]){
        if (process(arr, index, split - 1, false) == 0 /*代表中间的数全部消除掉，才能进行下面合并删除过程*/){
          result = Math.min(result, process(arr, split, R, true));
        }
      }
    }
    return Math.min(ans, result);
  }

  private static int natureWisdom(String str) {

    if (str == null || str.length() == 0){
      return 0;
    }

    if (str.length() < 2){
      return 1;
    }

    int ans = str.length();

    for (int i = 0; i < str.length(); i++){
      for (int j = i + 1; j < str.length(); j++){
        if (canDelete(str.substring(i, j + 1))){
          ans = Math.min(ans, natureWisdom(str.substring(0, i) + str.substring(j + 1, str.length())));
        }
      }
      
    }

    return ans;
  }

  public static boolean canDelete(String s) {
    char[] str = s.toCharArray();
    for (int i = 1; i < str.length; i++) {
      if (str[i - 1] != str[i]) {
        return false;
      }
    }
    return true;
  }

  private static String generateArray(int len, int variety) {
    char[] chars = new char[len];
    for (int i = 0; i < len; i++){
      chars[i] = (char) ((int)(Math.random() * variety) + 'a');
    }

    return String.valueOf(chars);
  }

  // 优良尝试的动态规划版本
  public static int restMin3(String s) {
    if (s == null) {
      return 0;
    }
    if (s.length() < 2) {
      return s.length();
    }
    char[] str = s.toCharArray();
    int N = str.length;
    int[][][] dp = new int[N][N][2];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < 2; k++) {
          dp[i][j][k] = -1;
        }
      }
    }
    return dpProcess(str, 0, N - 1, false, dp);
  }

  public static int dpProcess(char[] str, int L, int R, boolean has, int[][][] dp) {
    if (L > R) {
      return 0;
    }
    int K = has ? 1 : 0;
    if (dp[L][R][K] != -1) {
      return dp[L][R][K];
    }
    int ans = 0;
    if (L == R) {
      ans = (K == 0 ? 1 : 0);
    } else {
      int index = L;
      int all = K;
      while (index <= R && str[index] == str[L]) {
        all++;
        index++;
      }
      int way1 = (all > 1 ? 0 : 1) + dpProcess(str, index, R, false, dp);
      int way2 = Integer.MAX_VALUE;
      for (int split = index; split <= R; split++) {
        if (str[split] == str[L] && str[split] != str[split - 1]) {
          if (dpProcess(str, index, split - 1, false, dp) == 0) {
            way2 = Math.min(way2, dpProcess(str, split, R, all > 0, dp));
          }
        }
      }
      ans = Math.min(way1, way2);
    }
    dp[L][R][K] = ans;
    return ans;
  }

}
