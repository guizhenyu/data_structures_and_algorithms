package com.gzy.code.test;

public class Solution4 {


    public int solution(int[] A) {
        // Implement your solution here

        int n = A.length;

        int[] dp = new int[n];

        for(int i = 0; i < n; i++) {
            if(i == 0){
                dp[i] = 1;
            }else {
                dp[i] = 2;
            }
        }

        for (int i = 2; i < n; i++){

            if(A[i] == A[i - 2]){
                dp[i] = Math.max(dp[i - 1] + 1, dp[i]);
            }
        }

        int ans = 1;

        for (int i = 0; i < n; i++){

            if (dp[i] >= ans){

                ans = dp[i];
            }
        }

        return  ans;
    }
}
