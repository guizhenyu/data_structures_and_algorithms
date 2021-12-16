package com.gzy.code.fenwicktree;

public class Solution {
//
//    private int n = 6;
//    private int[] a = {1, 4, 2, 5, 1, 7};

    private int n = 4;
    private int[] a = {3,2,1,5};
    private FenwickTree fenwickTree;

    public Solution(){
        int maxValue = Integer.MIN_VALUE;
        for(int i = 0; i < n; ++i){
            if(maxValue < a[i]) {
                maxValue = a[i];
            }
        }
        fenwickTree = new FenwickTree(maxValue);
    }

    public int calInversions() {
        int x = 0;
        for(int i = 0; i < n; ++i){
            x += fenwickTree.sum(a[i]);
            fenwickTree.update(a[i], 1);

        }
        return n*(n-1)/2 - x;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calInversions());
    }

}
