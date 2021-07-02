package com.gzy.code.sort;

public class BubbleSort {

    private static int[] arr = new int[]{8,6,3,7,2,9,4,6,12,11};

    public static void main(String[] args) {

        sort(arr);
        for(int i = 0; i< arr.length; i++){
            System.out.println(arr[i]);
        }

    }

    public static void sort(int[] arr){

        for (int i = 0; i < arr.length - 1 ; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
        }

    }


}
