package com.gzy.code.leecode.offer.day2;

public class ReverseInt {


    public static int reverseInt(int num){

        boolean isNegative =  num >> 31 == 0? false : true;
        num *= (isNegative? -1 : 1);
        String str = String.valueOf(num);
        int newVal = 0;
        if(num >> 30 == 1){
            if(Integer.valueOf(str.charAt(str.length() - 1) - '0') > 2){
                return 0;
            }
        }
        for (int i = str.length() -1 ; i >= 0; i--) {
            int value = Integer.valueOf(str.charAt(i) - '0') * (int)Math.pow(10, i);
            if(isNegative){
                if ( Integer.MAX_VALUE - newVal  < value ){
                    //值溢出, return 0;

                    return 0;
                }
                newVal += value;
            }else {
                if(Integer.MAX_VALUE - newVal <= value){
                    //溢出
                    return 0;
                }
                newVal += value;
            }
        }

        return newVal * (isNegative? -1 : 1);

    }

    public static void main(String[] args) {
        int i = reverseInt(-123);
//        Integer.MIN_VALUE
        System.out.println(1 << 32);
    }
}
