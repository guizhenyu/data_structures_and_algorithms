package com.gzy.code.sort;

public class InsertSort {
    private static int[] arr = new int[]{8,7,3,7,2,9,4,6,12,11};
    public static void main(String[] args) {

        sort(arr);
        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }

    }

    public static void sort(int[] arr){

        if (arr.length == 1){
            return;
        }

        for (int i = 1; i < arr.length ; i++) {
            for (int j = i; j > 0; j --){
                if (arr[j] < arr[j-1]){
                    arr[j] = arr[j] ^ arr[j-1];
                    arr[j-1] = arr[j] ^ arr[j-1];
                    arr[j] = arr[j] ^ arr[j-1];

                }else {
                    break;
                }
            }
        }

    }
}
