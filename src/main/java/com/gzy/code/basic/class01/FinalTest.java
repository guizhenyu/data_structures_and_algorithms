package com.gzy.code.basic.class01;

/**
 * description: FinalTest date: 2022/9/2 17:23
 *
 * @author: guizhenyu
 */
public class FinalTest {



  public static void main(String[] args) {
    final String str = "HelloWorld！";//局部变量
    //局部内部类
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        for (int i = 0; i < 10; i++)
          System.out.println(str);
      }
    }).start();
    System.out.println("main thread finished");
  }

}
