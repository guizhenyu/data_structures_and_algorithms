package com.gzy.code.test;

public class Solution2Test {


    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        Solution31 solution31 = new Solution31();
        int maxLen = 9;
        int maxValue = 30;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++){
            String s = randomArry(maxLen);
            int ways1 = solution3.solution(s);
            int ways2 = solution31.solution(s);
//            int ways3 = dp1(arr, aim);
            if (ways1 != ways2 ){
                System.out.println("oops");
//                break;

            }
        }
        System.out.println("success");


    }



    private static String randomArry(int maxLen) {

        int n = (int)(maxLen * Math.random()) + 1;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++){

            sb.append((int)(10 * Math.random()));

        }

        return sb.toString();
    }

}
