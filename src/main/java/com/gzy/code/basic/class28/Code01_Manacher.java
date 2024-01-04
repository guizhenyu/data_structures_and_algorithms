package com.gzy.code.basic.class28;

/**
 * description: Code01_Manacher date: 2022/5/6 12:15
 *
 * @author: guizhenyu
 */
public class Code01_Manacher {

  /**
   *
   * 假设字符串str长度为N，想返回最长回文子串的长度
   *
   * 时间复杂度O(N)
   */


  public static char[] populateStringWithHash(String s){

    char[] chars = s.toCharArray();
    int len = chars.length;
    char[] ans  = new char[len * 2 + 1];

    int index = 0;
    for (int i = 0; i < ans.length; i++){

      if ((i & 1) == 0){
        ans[i] = '#';
      }else {
        ans[i] = chars[index++];
      }

    }

    return ans;

  }
  public static int  manacherNew(String s){

    char[] chars = populateStringWithHash(s);
    int n = chars.length;
    int[] pArr = new int[n];

    int max = 0;
    int maxIndex = -1;
    int R = -1;
    int C = -1;

    for (int i = 0; i < n; i++){
      int mirror_i = 2 * C - i;
      pArr[i] = R > i? Math.min(pArr[mirror_i], R - i) : 1;

      if (mirror_i >=0 && mirror_i - pArr[mirror_i] > 2 * C - R){
        continue;
      }

      while (i + pArr[i] < n && i - pArr[i] >= 0){
        if (chars[i + pArr[i]] == chars[i - pArr[i]]){
          pArr[i]++;
        }else {
          break;
        }
      }

      if (i + pArr[i] > R){
        C = i;
        R = C + pArr[i];
      }

      if (max < pArr[i]){
        max = pArr[i];
        maxIndex = i;
      }
    }

    return max - 1;


  }
  public static void main(String[] args) {
    int testTime = 100000;

    int maxLen = 30;

    int possibilities = 5;

    for (int i = 0; i < testTime; i++){

      String str = generateRandomStr(possibilities, maxLen);

      int ans = natureWisdom(str);

      int ans1 = manacherNew(str);
      if (ans != ans1){
        System.out.println("oops");
        System.out.println("str " + str);
        System.out.println("ans " + ans);
        System.out.println("ans1 " + ans1);
        System.out.println(right(str));
        break;
      }
    }
  }


  /**
   * todo: manacher 算法原理
   *      这边我们用求最长回文子序列去理解
   *      常规的暴力求解，时间复杂度是 O(N^2), 而 manacher能实现 O(N)的时间复杂度，准确的是2N, 但是忽略常数项2，就是N
   *      manacher肯定是用加速策略，避免了一些无效无重复的遍历，从而达到降低时间复杂度的效果
   *      怎么加速的：  ***************** 计算每个位置上的回文半径时，完成了下标的右移动并且没有回退，所以遍历完一次计算出所以的回文半径，就求出了最大回文半径，也就得到了答案
   *      1. 回文半径r, 每个字符都会有一个回文半径， 最小为0， 也就是他自己就是回文子串
   *      2. 中线点C, 也就是回文半径中心的下标索引, R 代表右边界 = C + r;  最右的扩成功位置的下一位, 也就是不包括
   *      3. 准备一个回文半径数组，跟字符串长度一样长 pArr[]
   *          pArr[i] = r  ->  i 代表字符对应的索引， r代表以 chars[i] 为回文子串的中心，半径长度
   *      4. 这边先将原始字符串，相邻的字符串都填充的了字符"#"，所以就将最长回文字符串转化成了求最长的pArr[index]值
   *      5. 遍历的步骤：
   *          i 代表当前遍历的索引位置
   *          C 代表现在所处的回文字符串的中心点的索引，初始值是 -1 代表不存在
   *          R 代表现在的回文字符串的右边界， 初始值为 -1 代表不存在
   *          5.1 先获取 i 对应的回文半径
   *              5.1.1 R > i 前一个面的回文半径一直没有更新
   *                    代表 i 还在之前的回文半径内,
   *                    pArr[i] = pArr[i'] = pArr[2 * C - i] (i 关于 C 对称点 i'的 )
   *                    这边 R > i时，取 mirror_i 的半径 跟 R - i 的最小值，是为了确保初始的 i + pArr[i] 不越界
   *                    如果越界了，就推翻了以C为中心点，R是他的右边界的前提条件
   *              5.1.2 如果 R <= i
   *                    R < i 代表 i 没有对称点， 那么 i 的初始尝试回文半径给个默认值 1
   *                    1 代表初始默认回文半径长度为 1， 考虑到最终返回时要 - 1， 所以 如果此时 a#b, i -> #, 那么该位置对应的最终是无效的，最终 1 - 1 = 0
   *                      而如果是 #a#, 那么初始回文长度是 1 ，后面判断再 + 1 ，最后返回还得 - 1， 最终还是 1， 也就是a对应的回文数长度是 1
   *                    R == i
   *                    虽然有对称点，但是对称点对应的回文半径越界了(i'的左边界 left(i')= i' - pArr[i'] 小于 2C - R)，对当前的i没有参考意义，所以也赋予一个默认值1
   *          5.2 从 i -> R, 遍历所有的索引，看是否可以更新pArr[i],这个过程其实就是加速的过程(因为不回退)
   *              i' 是 i 关于C的对称点
   *              5.2.1  i' 的左边界在 C的左边界内
   *                这种情况直接跳过， pArr[i] == pArr[i'], 由于是在大的以C为中心的回文串中，不需要比较
   *              5.2.2  i' 的左边界==C的左边界
   *                这种情况也要比较，由于边界不可达
   *              5.2.3  i' 的左边界小于C的左边界
   *                   得比较 chars[i - pArr[i] - 1] == (chars[i + pArr[i] +1] ？
   */
  private static int manacher(String str) {
    if (null == str || str.length() == 0){
      return 0;
    }

    char[] chars = populateChars(str);
    int ans = Integer.MIN_VALUE;
    int R = -1;
    int C = -1;
    int[] pArr = new int[chars.length];

    for (int i = 0; i < chars.length; i++){
      //  这边 R > i时，取 mirror_i 的半径 跟 R - i 的最小值，是为了确保初始的 i + pArr[i] 不越界
      //  越界了，会导致忽略 R+1 ~ i + pArr[i] 之间的数据还没有比较
      pArr[i] = R > i? Math.min(pArr[2 * C - i], R - i) : 1;
//      pArr[i] = R > i? pArr[2 * C - i] : 1;
      int mirror_i = 2 * C - i;
      if (mirror_i >= 0 && (mirror_i - pArr[mirror_i]) > (2 * C - R)){
        continue;
      }
      while (i + pArr[i] < chars.length && i - pArr[i] > -1){

//        if (mirror_i < 0){
//          break;
//        }
        // 4.2.1  i' - pArr[i'] < C - R break

//        // 4.2.2  i' - pArr[i'] == C - R
//        else if(mirror_i >= 0 && (mirror_i - pArr[mirror_i]) == (C - R)){
//          要计算
//        }
        // 4.2.3  i' - pArr[i'] > C - R

          if (chars[i + pArr[i]] == chars[i - pArr[i]]) {
            pArr[i]++;
          }else {
            break;
          }

      }

      if (i + pArr[i] > R){
        R = i + pArr[i];
        C = i;
      }

      ans = Math.max(ans, pArr[i]);
    }

    return ans - 1;
  }
//  private static int manacher(String str) {
//    if (null == str || str.length() == 0){
//      return 0;
//    }
//
//    char[] chars = populateChars(str);
//    int ans = Integer.MIN_VALUE;
//    int R = -1;
//    int C = -1;
//    int[] pArr = new int[chars.length];
//
//    for (int i = 0; i < chars.length; i++){
//
//      pArr[i] = R > i? pArr[2 * C - i] : 1;
//      while (i + pArr[i] < chars.length && i - pArr[i] > -1){
//        // 4.2.1  i' - pArr[i'] < C - R break
//        int mirror_i = 2 * C - i;
//        if (mirror_i < 0 || mirror_i - pArr[mirror_i] < 0 || (mirror_i - pArr[mirror_i]) > (C - R)){
//          break;
//        }
//        // 4.2.2  i' - pArr[i'] == C - R break
//        else if(mirror_i < 0  || mirror_i - pArr[mirror_i] < 0 || (mirror_i - pArr[mirror_i]) == (C - R)){
//          break;
//        }
//        // 4.2.3  i' - pArr[i'] > C - R
//        else {
//          if (chars[i + pArr[i]] == chars[i - pArr[i]]){
//            pArr[i]++;
//          }
//        }
//      }
//
//      if (i + pArr[i] > R){
//        R = i + pArr[i];
//        C = i;
//      }
//
//      ans = Math.max(ans, pArr[i]);
//    }
//
//    return ans - 1;
//  }

  private static int natureWisdom(String str) {
    if (null == str || str.length() == 0){
      return 0;
    }
    int ans = Integer.MIN_VALUE;

    char[] chars = populateChars(str);

    for (int i = 0; i < chars.length; i++){

      int l = i - 1;
      int r = i + 1;

      while (l >= 0 && r < chars.length && chars[l] == chars[r]){
        l--;
        r++;
      }
      ans = Math.max(ans, r - l - 1);
    }
    return ans / 2;
  }

  // for test
  public static int right(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
//    char[] str = manacherString(s);
    char[] str = populateChars(s);
    int max = 0;
    for (int i = 0; i < str.length; i++) {
      int L = i - 1;
      int R = i + 1;
      while (L >= 0 && R < str.length && str[L] == str[R]) {
        L--;
        R++;
      }
      max = Math.max(max, R - L - 1);
    }
    return max / 2;
  }


  public static char[] populateChars(String str){

    char[] chars = str.toCharArray();
    char[] res = new char[chars.length * 2 + 1];
    int index = 0;

    for (int i = 0; i < res.length; i++){
      res[i] = (i & 1) == 0 ? '#' : chars[index++];
    }
    return res;
  }

  private static String generateRandomStr(int possibilities, int maxLen) {
    char[] chars = new char[(int) (Math.random() * maxLen) + 2];
    for (int i = 0; i < chars.length; i++){
      chars[i] = (char)('a' + (int)(Math.random() * possibilities));
    }

    return String.valueOf(chars);
  }


  public static int manacher1(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    // "12132" -> "#1#2#1#3#2#"
    char[] str = populateChars(s);
    // 回文半径的大小
    int[] pArr = new int[str.length];
    int C = -1;
    // 讲述中：R代表最右的扩成功的位置
    // coding：最右的扩成功位置的，再下一个位置
    int R = -1;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < str.length; i++) { // 0 1 2
      // R第一个违规的位置，i>= R
      // i位置扩出来的答案，i位置扩的区域，至少是多大。
      pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
      while (i + pArr[i] < str.length && i - pArr[i] > -1) {
        if (str[i + pArr[i]] == str[i - pArr[i]]){
          pArr[i]++;}
        else {
          break;
        }
      }
      if (i + pArr[i] > R) {
        R = i + pArr[i];
        C = i;
      }
      max = Math.max(max, pArr[i]);
    }
    return max - 1;
  }
}
