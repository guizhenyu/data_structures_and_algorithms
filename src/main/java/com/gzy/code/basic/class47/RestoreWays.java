package com.gzy.code.basic.class47;

/**
 * description: RestoreWays date: 2022/5/30 14:58
 * // 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件：
 * // 1. 0位置的要求：arr[0]<=arr[1]
 * // 2. n-1位置的要求：arr[n-1]<=arr[n-2]
 * // 3. 中间i位置的要求：arr[i]<=max(arr[i-1],arr[i+1])
 * // 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0
 * // 请你根据上述条件，计算可能有多少种不同的arr可以满足以上条件
 * // 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种，即[6,9,9]达标
 * @author: guizhenyu
 */
public class RestoreWays {

  public static void main(String[] args) {
    int len = 4;
    int testTime = 15;

    for (int i = 0; i < testTime; i++){
      int N = (int)(Math.random() * len) + 2;
      int[] arr = generateArr(N);

      int ans0 = natureWisdom(arr);
      int ans1 = ways1(arr);
      int ans2 = ways2(arr);
      int ans3 = ways3(arr);
      if(ans1 != ans0 || ans2 != ans1 || ans2 != ans3){
        int i1 = ways0(arr);
        System.out.println("oops!");
        return;
      }
    }
  }


  public static int ways0(int[] arr) {
    return process00(arr, 0);
  }

  public static int process00(int[] arr, int index) {
    if (index == arr.length) {
      return isValid(arr) ? 1 : 0;
    } else {
      if (arr[index] != 0) {
        return process00(arr, index + 1);
      } else {
        int ways = 0;
        for (int v = 1; v < 201; v++) {
          arr[index] = v;
          ways += process00(arr, index + 1);
        }
        arr[index] = 0;
        return ways;
      }
    }
  }

  public static boolean isValid0(int[] arr) {
    if (arr[0] > arr[1]) {
      return false;
    }
    if (arr[arr.length - 1] > arr[arr.length - 2]) {
      return false;
    }
    for (int i = 1; i < arr.length - 1; i++) {
      if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
        return false;
      }
    }
    return true;
  }


  /**
   * 优化思路:
   *      从后往前计算结果， 0 <-- n - 1
   *      来到每个数时，他后面的已经确定的数，相比当前的数有3中可能：
   *      // 如果i位置的数字变成了v,
   * 	    // 并且arr[i]和arr[i+1]的关系为s，
   *    	// s==0，代表arr[i] < arr[i+1] 右大
   * 	    // s==1，代表arr[i] == arr[i+1] 右=当前
   * 	    // s==2，代表arr[i] > arr[i+1] 右小
   *
   *
   *
   *
   *
   *
   * @param arr
   * @return
   */
  private static int ways1(int[] arr) {
    int n = arr.length;
    if (arr[n - 1] != 0){
      // 为什么这边传的是2， 可以简单理解成，n位不存在，所以arr[n] < arr[n - 1]

      return process1(arr, n - 1, arr[n - 1], 2);
    }else {
      int ans = 0;
      for (int i = 1; i < 201; i++){
        ans += process1(arr, n - 1, i, 2);
      }
      return ans;
    }

  }

  /**
   *
   *
   * @param arr
   * @param index
   * @param value
   * @param compare
   * @return
   */
  private static int process1(int[] arr, int index, int value, int compare) {

    // 通过可变参数，来确定base case
    // 当索引来到0时，就是快结束了，判断一下是否符合条件
    if (index == 0){
      // 如果是 小于等于后一个数，
      return ((compare == 0 || compare == 1) && (arr[0] == 0 || arr[0] == value))? 1 : 0;
    }

    // index > 0
    if (arr[index] != 0 && arr[index] != value){
      return 0;
    }

    int ans = 0;
    if (compare == 0 || compare == 1){
      // arr[index] <= arr[index + 1];

      for (int i = 1; i < 201; i++){
        ans += process1(arr, index - 1, i,
            (i < value? 0 : (i == value? 1 : 2)));
      }
    }else{
      // compare == 2, 代表 arr[index] > arr[index + 1], 那么arr[index - 1] 的数必须要大于等于value
      for (int i = value; i < 201; i++){
        ans += process1(arr, index - 1, i, i > value? 2 : 1);
      }
    }

    return ans;
  }

  public static int process1Optimize(int[] arr, int index, int value, int s){
    if (index == 0){
      return (s == 0 || s ==1) && (arr[0] == 0 || arr[0] == value)? 1 : 0;
    }

    if (arr[0] != 0 && arr[0] != value){
      return 0;
    }

    int ans = 0;
    if (s == 1 || s == 0){
      for (int i = 1; i < value; i++){
        ans += process1Optimize(arr, index - 1, i, 0);
      }
    }
    ans += process1Optimize(arr, index, value, 1);

    for (int i = value + 1; i < 201; i++){
      ans += process1Optimize(arr, index, value, 2);
    }

    return ans;

  }


  private static int natureWisdom(int[] arr) {

    return process0(arr, 0);
  }

  private static int process0(int[] arr, int index) {

    if (index == arr.length){
      return isValid(arr)? 1 : 0;
    }else {
      int ans = 0;
      if (arr[index] != 0){
        return process0(arr, index + 1);
      }
      for (int i = 1; i <= 200; i++){

        arr[index] = i;
        ans += process0(arr, index + 1);

      }
      arr[index] = 0;
      return ans;
    }
  }

  // 1. 0位置的要求：arr[0]<=arr[1]
  // 2. n-1位置的要求：arr[n-1]<=arr[n-2]
  // 3. 中间i位置的要求：arr[i]<=max(arr[i-1],arr[i+1])
  private static boolean isValid(int[] arr) {
    if (arr[0] > arr[1]){
      return false;
    }
    int n = arr.length;
    if (arr[n - 1] > arr[n - 2]){
      return false;
    }
    for (int i = 1; i < n - 1; i++){

      if (arr[i] > Math.max(arr[i - 1], arr[i + 1])){
        return false;
      }
    }
    return true;
  }

  private static int[] generateArr(int n) {
    int[] res = new int[n];

    for (int i = 0; i < n; i++){

      if (Math.random() > 0.5){
        res[i] = (int)(200 * Math.random()) + 1;
      }
    }
    return res;
  }

  private static int ways2(int[] arr) {
    int n = arr.length;

    int[][][] dp = new int[n][201][3];

    if (arr[0] != 0){
      dp[0][arr[0]][0] = 1;
      dp[0][arr[0]][1] = 1;
    }else {
      for (int i = 1; i < 201; i++){
        dp[0][i][0] = 1;
        dp[0][i][1] = 1;
      }
    }


    for (int i = 1; i < n; i++){
      for (int v  = 1; v < 201; v++){
        for (int s = 0; s < 3; s++){
          if (arr[i] == 0 || v == arr[i]){
            if (s == 0 || s == 1){
              for (int pre = 1; pre < v; pre++){
                dp[i][v][s] += dp[i - 1][pre][0];
              }
            }
            dp[i][v][s] += dp[i - 1][v][1];
            for (int pre = v + 1; pre < 201; pre++){
              dp[i][v][s] += dp[i - 1][pre][2];
            }
          }
        }
      }
    }

    if (arr[n - 1] != 0){
      return dp[n - 1][arr[n - 1]][2];
    }else {
      int ways = 0;
      for (int i = 1; i < 201; i++){
        ways += dp[n - 1][i][2];
      }
      return ways;
    }
  }


  public static int ways3(int[] arr){
    int n = arr.length;
    int[][][] dp = new int[n][201][3];

    if (arr[0] != 0){
      dp[0][arr[0]][0] = 1;
      dp[0][arr[0]][1] = 1;
    }else {
      for (int i = 1; i < 201; i++){
        dp[0][i][0] = 1;
        dp[0][i][1] = 1;
      }
    }

    int[][] dpPreSum = new int[201][3];
    for (int i = 1; i < 201; i++){
      for (int s = 0; s < 3; s++){
      dpPreSum[i][s] = dpPreSum[i - 1][s] + dp[0][i][s];
      }
    }

    for (int i = 1; i < n; i++){
      for (int v = 1; v < 201; v++){
        for (int s = 0; s < 3; s++){
          if (arr[i] == 0 || arr[i] == v){
            if (s == 0 || s == 1){
              dp[i][v][s] += sum(dpPreSum, 0, 1, v - 1);
            }
            dp[i][v][s] += dp[i - 1][v][1];
            dp[i][v][s] += sum(dpPreSum, 2, v + 1, 200);
          }
        }
      }

      for (int v = 1; v < 201; v++){
        for (int s = 0; s < 3; s++){
          dpPreSum[v][s] = dpPreSum[v - 1][s] + dp[i][v][s];
        }
      }
    }

    if (arr[n -1] != 0){
      return dp[n - 1][arr[n - 1]][2];
    }else {
      int ways = 0;
      for (int i = 1; i < 201; i++){
        ways += dp[n - 1][i][2];
      }
      return ways;
    }
  }

  public static int sum(int[][] preSum, int s, int start, int end){
    return preSum[end][s] - preSum[start - 1][s];
  }
}
