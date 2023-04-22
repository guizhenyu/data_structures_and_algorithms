package jdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DateUtil {

    private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> {
                System.out.println("new simpleDateFormat");
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            });

    public static Date parse(String dateString) {
        Date date = null;
        try {
            date = dateFormatThreadLocal.get().parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                System.out.println(DateUtil.parse("2022-07-24 16:34:30"));
            });
        }
        executorService.shutdown();
    }
}
