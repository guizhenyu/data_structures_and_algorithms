package com.gzy.code.basic.class02;

/**
 * description: EvenTimeOddTimes date: 2021/12/22 10:43 上午
 *
 * @author: guizhenyu
 */
public class EvenTimeOddTimes {



  /**
   * arr中， 只有一个数出现奇数次，其他出现偶数次，求出现奇数次的数
   *
   * @param arr
   * @return
   */
  public static int printOddTimesNum1(int[] arr){
    int ans = 0;
    for (int num : arr) {
      ans ^= num;
    }
    return ans;
  }


  /**
   * arr中， 有两个数出现奇数次，其他出现偶数次，求这两个出现奇数次的数
   *
   * @param arr
   */
  public static void printOddTimesNum2(int[] arr){

    int eor = 0;
    for (int i = 0; i < arr.length; i++){
      eor ^= arr[i];
    }

    // 此时 eor就是 出现奇数次数之间的 异或结果
    // ******* 有个知识点
    // 一个数转换成二进制，怎么求最右边的是1的数
    // rightOne = eor ^ (-eor)
    // todo: 或者是 rightOne = eor &(~eor + 1)
    int rightOne = eor ^ (-eor);

    int firstOddTimesNum = 0;
    for (int i = 0; i < arr.length; i++){
      if ((arr[i] ^ rightOne) != 0){
        // 只要最右边是1的数才会进来， 如果是偶数的数最终会消掉，只剩奇数的数
        firstOddTimesNum ^= arr[i] ;
      }
    }

    int secondOddTimesNum = eor ^ firstOddTimesNum;

    System.out.println(firstOddTimesNum);
    System.out.println(secondOddTimesNum);


  }


  public static void main(String[] args) {
    int a = 5;
    int b = 7;

    a = a ^ b;
    b = a ^ b;
    a = a ^ b;

    System.out.println(a);
    System.out.println(b);

    int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
    printOddTimesNum1(arr1);

    int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
    printOddTimesNum2(arr2);

  }

}

