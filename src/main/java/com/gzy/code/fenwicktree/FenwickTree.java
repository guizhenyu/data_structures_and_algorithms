package com.gzy.code.fenwicktree;

public class FenwickTree {


    private int n;

    private int c[];

    public FenwickTree(int n){
        this.n = n;
        c = new int[n + 1];

        for (int i = 1; i <= n; ++i){
            c [i] = 0;
        }
    }

    private int lowbit(int i){
        return i & (-i);
    }

    public int sum(int i){
        int s = 0;
        while(i > 0){
            s += c[i];
            i -= lowbit(i);
        }
        return s;
    }

    public void update(int i, int delta){
        while (i <= n){
            c[i] += delta;
            i += lowbit(i);
        }
    }
}
