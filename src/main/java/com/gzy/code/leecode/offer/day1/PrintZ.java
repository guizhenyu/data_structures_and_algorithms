package com.gzy.code.leecode.offer.day1;

public class PrintZ {

    public static String convert(String s, int numRows) {

        if (numRows == 1){
            System.out.println(s);
        }
        StringBuilder[] sbArr = new StringBuilder[numRows];
        int sbArrMaxIndex = numRows - 1;
        // 记录下一个 sbArrIndex
        int sbArrIndex = 0;
        // 记录索引是增还是减，因为是Z形状的字符串
        boolean isIncrement = true;
        // 需要填充的空字符串标量
        for(int i = 0; i < s.length(); i++){

            if(sbArr[sbArrIndex] == null){
                sbArr[sbArrIndex] = new StringBuilder();
            }

//            int appendBlankNum  = Math.abs(Math.abs(sbArrIndex -(isIncrement? 0 : sbArrMaxIndex)) - appendBlankStand);
            sbArr[sbArrIndex].append(s.charAt(i));
//            for (int j = 0; j < appendBlankNum; j++) {
//                sbArr[sbArrIndex].append(" ");
//            }


            sbArrIndex += isIncrement? 1 : -1;
            if (sbArrIndex == 0 || sbArrIndex == sbArrMaxIndex){
                isIncrement = !isIncrement;
            }
        }


        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < numRows; i++){
            if(sbArr[i] != null){
                ans.append(sbArr[i].toString());
            }

        }

        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 4));
    }

}
