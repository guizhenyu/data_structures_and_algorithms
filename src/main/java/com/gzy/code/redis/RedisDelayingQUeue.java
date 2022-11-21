//package com.gzy.code.redis;
//
//import com.alibaba.fastjson.JSON;
//import java.lang.reflect.Type;
//import com.alibaba.fastjson.TypeReference;
//import java.util.Set;
//import java.util.UUID;
//import redis.clients.jedis.Jedis;
//
///**
// * description: RedisDelayingQUeue date: 2021/11/27 10:09 上午
// *
// * @author: guizhenyu
// */
//public class RedisDelayingQUeue<T> {
//
//  static class TaskItem<T>{
//    public String id;
//    public T msg;
//  }
//
//  private Type TaskType = new TypeReference<TaskItem<T>>(){}.getType();
//
//  private Jedis jedis;
//
//  private String queueKey;
//
//  public RedisDelayingQUeue(Jedis jedis, String queueKey){
//
//    this.jedis = jedis;
//    this.queueKey = queueKey;
//  }
//
//  public void delay(T msg){
//    TaskItem task = new TaskItem();
//    task.id = UUID.randomUUID().toString();
//    task.msg = msg;
//    String s = JSON.toJSONString(task);
//
//    jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
//  }
//
//  public void loop() {
//    while (!Thread.interrupted()){
//      Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
//      if (values.isEmpty()){
//        try{
//          Thread.sleep(500);
//        }catch (InterruptedException e){
//          break;
//        }
//        continue;
//      }
//
//
//      String s = values.iterator().next();
//      if (jedis.zrem(queueKey, s) > 0){
//        TaskItem taskItem = (TaskItem)JSON.parseObject(s, TaskType);
//
//        this.handleMsg(taskItem.msg);
//      }
//    }
//  }
//
//  private void handleMsg(Object msg) {
//
//    System.out.println(msg);
//  }
//
//  public static void main(String[] args) {
//    Jedis jedis = new Jedis("127.0.0.1", 6379);
//    jedis.auth("123456");
//
//    RedisDelayingQUeue<Object> delayQueue = new RedisDelayingQUeue<>(jedis, "delay_queue");
//
//    Thread producer = new Thread() {
//      @Override
//      public void run(){
//
//        for (int i = 0; i < 10; i ++){
//          delayQueue.delay("codehelo" + i);
//        }
//      }
//    };
//
//    Thread consumer = new Thread() {
//
//      @Override
//      public void run() {
//        delayQueue.loop();
//      }
//    };
//
////    producer.start();
//
//    consumer.start();
//
//
//    try{
////      producer.join();
//      Thread.sleep(6000);
//      consumer.interrupt();
//      consumer.join();
//    }catch (InterruptedException e){
//
//    }
//
//
//  }
//
//
//}
