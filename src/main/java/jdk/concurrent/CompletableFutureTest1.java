package jdk.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest1 {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
//        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("执行step 1");
//            return "step1 result";
//        }, executor);
//        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
//            System.out.println("执行step 2");
//            return "step2 result";
//        });
//        cf1.thenCombine(cf2, (result1, result2) -> {
//            System.out.println(result1 + " , " + result2);
//            System.out.println("执行step 3");
//            return "step3 result";
//        }).thenAccept(result3 -> System.out.println(result3));


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行step 1");
            return "step1 result";
        });

        cf1.thenRun(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行step 2");

        });

        for (;;){

        }


//        try {
//            CompletableFuture<Void> d = new CompletableFuture<Void>();
//
//            d.thenRun(() -> {
//                System.out.println("我工作啦");
//            });
//            System.out.println(d.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }


    }



    static List<String> getUserIdList(){
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        List<String> uuidList = new ArrayList<>();
        uuidList.add("1");
        uuidList.add("2");
        uuidList.add("3");
        return uuidList;
    }

    static CompletableFuture<Integer> getUserGrade(String uid){

        return CompletableFuture.supplyAsync(() -> {
           try{
               Thread.sleep(500);
           }catch (InterruptedException e){
               e.printStackTrace();
           }

            return 10;
        });

    }
}
