package jdk;

public class ThreadLocalTest {







    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("你好");

                System.out.println(threadLocal.get());

                System.gc();
//                threadLocal = null;
                try {
                    Thread.sleep(5000l);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(threadLocal.get());

            }
        }).start();

    }
}
