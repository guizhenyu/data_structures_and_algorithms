//package com.gzy.code.zcy;
//
//
///**
// * 洗咖啡时间最少
// * 前提：
// * 1. 员工喝咖啡结束的时间是串行的，比如 1，3，6，9，对应的分别是1时间，3时间，6时间，9时间喝完
// * 2. 洗咖啡的两种方式：
// *    2.1 用咖啡机洗， 洗一个要3分钟
// *    2.2 自然风干，需要10分钟
// *
// *
// *
// */
//public class WashCoffeeCupSolution {
//
//
//    public static void main(String[] args) {
//        int[] washTime = {1,3,6,9,10,13,16,20,25,30};
//
//        System.out.println(coffeeCupWashTimeLeast(washTime, 3, 10));
//        System.out.println(coffeeCupWashTimeLeast1(washTime, 3, 10));
//        System.out.println(dp(washTime, 3, 10));
//
//
//    }
//
//
//    public int violenceMethod(int[] washTime, int machineSPeed, int natureSpeed, int index, int costTime){
//        if(index == washTime.length){
//            return costTime;
//        }
//
//
//
//
//
//
//
//    }
//    /**
//     *
//     * @param washTime 员工喝咖啡结束的时间
//     * @param coffeeMachineSpeed 咖啡机洗杯子的时间速度
//     * @param natureSpeed 自然风干杯子的时间
//     * @return
//     */
//    public static int coffeeCupWashTimeLeast(int[] washTime, int coffeeMachineSpeed, int natureSpeed){
//
//
//        return process(washTime, coffeeMachineSpeed, natureSpeed, 0, 0);
//    }
//    public static int coffeeCupWashTimeLeast1(int[] washTime, int coffeeMachineSpeed, int natureSpeed){
//
//
//        return process1(washTime, coffeeMachineSpeed, natureSpeed, 0, 0);
//    }
//
//
//
//    /**
//     * 方法一： 暴力递归
//     *
//     * @param washTime  固定变量
//     * @param coffeeMachineSpeed 固定变量
//     * @param natureSpeed 固定变量
//     * @param index 杯子的索引
//     * @param timeCount 已经花费的时间
//     * @return
//     */
//    public static int process(int[] washTime, int coffeeMachineSpeed, int natureSpeed, int index, int timeCount){
//        if (index + 1 == washTime.length){
//            // 最后一个杯子,只有两种选择
//            // 机器洗和自然风干
//            return Math.min(
//                    Math.max(washTime[index], timeCount) + coffeeMachineSpeed,
//                    Math.max(washTime[index] + natureSpeed, timeCount)
//            );
//        }
//
//        // 不是最后一个杯子，也是两种选择
//        // 机器洗
//        timeCount = Math.max(timeCount, washTime[index]) + coffeeMachineSpeed;
//        int nextTimeCount = process(washTime, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);
//
//        int p1 = Math.max(timeCount, nextTimeCount);
//
//        // 自然风干
//        timeCount = Math.max(washTime[index] + natureSpeed, timeCount);
//        nextTimeCount = process(washTime, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);
//
//        int p2 = Math.max(timeCount, nextTimeCount);
//
//        return Math.min(p1, p2);
//
//    }
//
//    /**
//     * 方法一： 暴力递归
//     *
//     * @param drink  固定变量
//     * @param coffeeMachineSpeed 固定变量
//     * @param natureSpeed 固定变量
//     * @param index 杯子的索引
//     * @param washLine 已经花费的时间
//     * @return
//     */
//    public static int process1(int[] drink, int coffeeMachineSpeed, int natureSpeed, int index, int washLine){
//        if (index + 1 == drink.length){
//            // 最后一个杯子,只有两种选择
//            // 机器洗和自然风干
//            return Math.min(
//                    Math.max(drink[index], washLine) + coffeeMachineSpeed,
//                    drink[index] + natureSpeed
//            );
//        }
//
//        // 不是最后一个杯子，也是两种选择
//        // 机器洗
//        int washLine1 = Math.max(washLine, drink[index]) + coffeeMachineSpeed;
//        int nextTimeCount = process(drink, coffeeMachineSpeed, natureSpeed, index + 1, washLine1);
//
//        int p1 = Math.max(washLine1, nextTimeCount);
//
//        // 自然风干
//        int washLine2 = drink[index] + natureSpeed;
//        nextTimeCount = process(drink, coffeeMachineSpeed, natureSpeed, index + 1, washLine);
//
//        int p2 = Math.max(washLine2, nextTimeCount);
//
//        return Math.min(p1, p2);
//
//    }
//
//    public static int dp(int[] drink, int coffeeMachineSpeed, int natureSpeed){
//
//        int N = drink.length;
//
//        if (coffeeMachineSpeed >= natureSpeed){
//            return drink[N - 1] + natureSpeed;
//        }
//
//        int limit = 0;
//        for (int i = 0; i < N; i ++){
//            limit = Math.max(limit, drink[i]) + coffeeMachineSpeed;
//        }
//
//        int[][] dp = new int[N][limit + 1];
//
//        // 先求出 最后一个杯子的所有情况
//        for (int i = 0; i <= limit; i ++){
//            dp[N - 1][i] = Math.min(
//                Math.max(drink[N - 1], i) + coffeeMachineSpeed,
//                drink[N - 1] + natureSpeed
//            );
//        }
//
//
//        for (int index = N - 2; index >=0; index --){
//            for (int wash = 0; wash <= limit; wash ++){
//
//
//                int p1 = Integer.MAX_VALUE;
//                int washLine1 = Math.max(wash, drink[index]) + coffeeMachineSpeed;
//                if (washLine1 <= limit){
//                    p1 = Math.max(washLine1, dp[index + 1][washLine1]);
//                }
//
//                // 自然风干
//                int washLine2 = drink[index] + natureSpeed;
//                int p2 = Math.max(washLine2 , dp[index + 1][wash]);
//                dp[index][wash] = Math.min(p1, p2);
//            }
//
//        }
//
//
//
//
//        return dp[0][0];
//    }
//}
