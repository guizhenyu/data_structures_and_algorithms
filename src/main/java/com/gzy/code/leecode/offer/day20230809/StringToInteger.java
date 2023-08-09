package com.gzy.code.leecode.offer.day20230809;

public class StringToInteger {
    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     * <p>
     * 0 <= s.length <= 200
     * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
     *
     * @param s
     * @return
     */

    public static int myAtoi(String s) {

        if (s == null || s == "") {
            return 0;
        }

        int ans = 0;
        boolean isNegative = false;
        boolean preIsBlank = true;

        for (int i = 0; i < s.length(); i++) {
            int cur = (int) s.charAt(i);

            if (cur == 45) {
                // current char  is '-'
                if (!preIsBlank){
                    return ans * (isNegative ? -1 : 1);
                }
                preIsBlank = false;

                if (ans == 0) {
                    isNegative = true;
                } else {
                    return ans * (isNegative ? -1 : 1);
                }
            } else if (cur == 43) {
                if (!preIsBlank){
                    return ans * (isNegative ? -1 : 1);
                }
                preIsBlank = false;


                if (ans != 0) {
                    return ans * (isNegative ? -1 : 1);
                }
            } else if (cur >= 48 && cur <= 57) {
                // current char is among 48 and 57
                preIsBlank = false;
                cur -= 48;
                if (isNegative) {
                    if ((Integer.MIN_VALUE + cur) / 10 > -ans) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if ((Integer.MAX_VALUE - cur) / 10 < ans) {
                        return Integer.MAX_VALUE;
                    }
                }

                ans = ans * 10 + cur;

            } else {
                if (!preIsBlank){
                    return ans * (isNegative ? -1 : 1);
                }
                // other useless chars
                if (cur == 32 && ans == 0) {
                    continue;
                }
                return ans * (isNegative ? -1 : 1);
            }
        }
        return ans * (isNegative ? -1 : 1);
    }

    public static void main(String[] args) {
        String s = "2147483646";
//
        System.out.println(myAtoi(s));

        System.out.println((int) '+');
    }


}
