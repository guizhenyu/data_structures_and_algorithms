package jdk.concurrent;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {


    public static void main(String[] args) {
        CompletableFuture[] completableFutures = getUserIdList().stream().map( uuid ->
            getUserGrade(uuid)).toArray(CompletableFuture[]:: new);


        CompletableFuture.allOf(completableFutures).thenApply(v -> Stream.of(completableFutures).map(future -> {
            try{
                return future.get();
            }catch (Exception e){
               throw new RuntimeException(e);
            }
        }).collect(Collectors.toList())).whenComplete((userGradeList, e) ->{
            if (e != null){
                throw new RuntimeException(e);
            }
            System.out.println(userGradeList);
        }).join();




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
