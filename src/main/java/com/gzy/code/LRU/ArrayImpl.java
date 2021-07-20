package com.gzy.code.LRU;

/**
 * 数组实现
 *
 */

public class ArrayImpl {

    private String[] arr;

    private int capicity;
    public ArrayImpl(int capicity){
        capicity = capicity;
        arr = new String[capicity];
    }

    public void use(String key){

        for (int i = 0; i < capicity; i++ ){
            if(arr[i] == null){
                arr[i] = key;
                return;
            }
        }

        String[] newArr = new String[capicity];
        for (int i = 1; i < capicity; i++ ){
            newArr[i - 1] = arr[i];
        }

        newArr[capicity - 1] = key;
        arr = newArr;
    }

}
