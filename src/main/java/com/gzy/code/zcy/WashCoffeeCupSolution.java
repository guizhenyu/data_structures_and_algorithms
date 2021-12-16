package com.gzy.code.zcy;


/**
 * 洗咖啡时间最少
 * 前提：
 * 1. 员工喝咖啡结束的时间是穿行的，比如 1，3，6，9，对应的分别是1时间，3时间，6时间，9时间喝完
 * 2. 洗咖啡的两种方式：
 *    2.1 用咖啡机洗， 洗一个要3分钟
 *    2.2 自然风干，需要10分钟
 *
 *
 *
 */
public class WashCoffeeCupSolution {


    public static void main(String[] args) {
        int[] washTime = {1,3,6,9,10,13,16,20,25,30};

        System.out.println(coffeeCupWashTimeLeast(washTime, 3, 10));


    }
    /**
     *
     * @param washTime 员工喝咖啡结束的时间
     * @param coffeeMachineSpeed 咖啡机洗杯子的时间速度
     * @param natureSpeed 自然风干杯子的时间
     * @return
     */
    public static int coffeeCupWashTimeLeast(int[] washTime, int coffeeMachineSpeed, int natureSpeed){


        return process(washTime, coffeeMachineSpeed, natureSpeed, 0, 0);
    }



    /**
     * 方法一： 暴力递归
     *
     * @param washTime  固定变量
     * @param coffeeMachineSpeed 固定变量
     * @param natureSpeed 固定变量
     * @param index 杯子的索引
     * @param timeCount 已经花费的时间
     * @return
     */
    public static int process(int[] washTime, int coffeeMachineSpeed, int natureSpeed, int index, int timeCount){
        if (index + 1 == washTime.length){
            // 最后一个杯子,只有两种选择
            // 机器洗和自然风干
            return Math.min(
                    Math.max(washTime[index], timeCount) + coffeeMachineSpeed,
                    Math.max(washTime[index] + natureSpeed, timeCount)
            );
        }

        // 不是最后一个杯子，也是两种选择
        // 机器洗
        timeCount = Math.max(timeCount, washTime[index]) + coffeeMachineSpeed;
        int nextTimeCount = process(washTime, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);

        int p1 = Math.max(timeCount, nextTimeCount);

        // 自然风干
        timeCount = Math.max(washTime[index] + natureSpeed, timeCount);
        nextTimeCount = process(washTime, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);

        int p2 = Math.max(timeCount, nextTimeCount);

        return Math.min(p1, p2);

    }

    /**
     * 方法一： 暴力递归
     *
     * @param washTime  固定变量
     * @param coffeeMachineSpeed 固定变量
     * @param natureSpeed 固定变量
     * @param index 杯子的索引
     * @param timeCount 已经花费的时间
     * @return
     */
    public static int process1(int[] drink, int coffeeMachineSpeed, int natureSpeed, int index, int washLine){
        if (index + 1 == drink.length){
            // 最后一个杯子,只有两种选择
            // 机器洗和自然风干
            return Math.min(
                    Math.max(drink[index], washLine) + coffeeMachineSpeed,
                    Math.max(drink[index] + natureSpeed, timeCount)
            );
        }

        // 不是最后一个杯子，也是两种选择
        // 机器洗
        Math.max(washLine, drink[index]) + coffeeMachineSpeed;
        int nextTimeCount = process(drink, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);

        int p1 = Math.max(timeCount, nextTimeCount);

        // 自然风干
        timeCount = Math.max(drink[index] + natureSpeed, timeCount);
        nextTimeCount = process(drink, coffeeMachineSpeed, natureSpeed, index + 1, timeCount);

        int p2 = Math.max(timeCount, nextTimeCount);

        return Math.min(p1, p2);

    }
}
