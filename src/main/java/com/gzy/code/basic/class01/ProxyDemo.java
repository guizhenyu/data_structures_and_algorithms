package com.gzy.code.basic.class01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * description: ProxyDemo date: 2022/8/24 09:22
 *
 * @author: guizhenyu
 */
public class ProxyDemo {

  public static void main(String[] args) throws Exception {
    // 将创建的代理类$Proxy0的字节码保存
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    // 创建回调函数
    InvocationHandler handler = new MyInvocationHandler();
    // 创建代理类
    Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
    // 生成代理对象
    Foo f = (Foo) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
    // 调用代理对象方法
    f.foo();

    f.foo1();
    f.toString();
  }
  // 回调处理器
  private static class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (method.getName().equals("foo")){
        System.out.println(" 我代理你了");
        System.out.println(method.getName());
      }
      if (method.getName().equals("foo1")){
        System.out.println(" 我代理你了foo1");
        System.out.println(method.getName());
      }

      return null;
    }
  }
  private interface Foo {
   default void foo(){
      System.out.println("hello foo");
    }
   void foo1();



  }

}
