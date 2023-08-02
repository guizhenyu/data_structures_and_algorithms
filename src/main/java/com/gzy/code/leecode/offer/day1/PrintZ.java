package com.gzy.code.leecode.offer.day1;

public class PrintZ {

    public static String convert(String s, int numRows) {
        StringBuilder[] sbArr = new StringBuilder[numRows];
        for(int i = 0; i < s.length(); i++){
            packageZ(sbArr, s, i);
        }



        for(int i = 0; i < numRows; i++){
            if(sbArr[i] != null){
                System.out.println(sbArr[i].toString());
            }

        }
    }

    public static void packageZ(StringBuilder[] sbArr, String str, int index){
        int row = sbArr.length;
        int appendFlag = 0;

        if(row == 1){
            sbArr[0].append(str.charAt(index));
            return;
        }
        appendFlag = row -1;
        char cha = str.charAt(index);
        int rowIndex = index % row;
        ++index;
        if (index % appendFlag)

    }
    public static void main(String[] args) {

    }

}
