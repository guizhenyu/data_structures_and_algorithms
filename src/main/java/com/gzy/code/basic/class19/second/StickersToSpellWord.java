package com.gzy.code.basic.class19.second;

/**
 * description: StickersToSpellWord date: 2022/7/26 13:25
 * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 *
 * 您想要拼写出给定的字符串 target  ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 *
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stickers-to-spell-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: guizhenyu
 */
public class StickersToSpellWord {

  public int minStickers(String[] stickers, String target) {
    //思路：
    //    1 暴力递归
    //      一张一张去遍历

    int ans = process1(stickers, target);

    return ans == Integer.MAX_VALUE? -1 : ans;
  }

  private int process1(String[] stickers, String target) {

    if (target.length() == 0){
      return 0;
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < stickers.length; i++){
      // 用当前的贴纸去跟 目标字符串做匹配，返回匹配剩下组成的字符串
      String rest = minus(target, stickers[i]);

      if (rest.length() == target.length()){
        // 证明没有匹配的字符，直接进行下一个匹配，不会造成死循环
        continue;
      }

      ans = Math.min(ans, process1(stickers, rest));

    }


    return ans == Integer.MAX_VALUE? Integer.MAX_VALUE : ans + 1;

  }

  private String minus(String target, String rest) {
    char[] str1 = target.toCharArray();
    char[] str2 = rest.toCharArray();
    // 代表每个字母有几个
    int[] count = new int[26];

    for (int i = 0; i < str1.length; i++){
      count[str1[i] - 'a']++;
    }


    for (int i = 0; i < str2.length; i++){
      count[str1[i] - 'a']--;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++){
      if (count[i] > 0){
        for (int c = 0; c < count[i]; c++){
          sb.append((char) (c + 'a'));
        }
      }
    }

    return sb.toString();
  }


}
