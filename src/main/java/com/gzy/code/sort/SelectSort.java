package com.gzy.code.sort;

public class SelectSort {
    private static int[] arr = new int[]{8,6,3,7,2,9,4,6,12,11};
    public static void main(String[] args) {

        sort(arr);
        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }

    }

    public static void sort(int[] arr){

        for (int i = 0; i < arr.length - 1 ; i++) {
            int swapIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[swapIndex]){
                    swapIndex = j;
                }
            }
            if (swapIndex != i){
                arr[i] = arr[i] ^ arr[swapIndex];
                arr[swapIndex] = arr[i] ^ arr[swapIndex];
                arr[i] = arr[i] ^ arr[swapIndex];
            }
        }



    }
}
