package com.gzy.code.basic.class07HeapShow;

import com.gzy.code.basic.class07HeapShow.EveryStepShowBoss.CandidateComparator;
import com.gzy.code.basic.class07HeapShow.EveryStepShowBoss.Customer;
import com.gzy.code.basic.class07HeapShow.EveryStepShowBoss.DaddyComparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: WhoYourDaddy date: 2022/1/10 5:35 下午
 *
 * @author: guizhenyu
 */
public class WhoYourDaddy {


  /**
   * 候选人的大根结构堆
   *
   */
  public HeapCreater<Customer> candHeap;

  /**
   * 抽奖池的小根堆结构
   *
   */
  public HeapCreater<Customer> daddyHeap;


  public Map<Integer, Customer> customerMap;

  /**
   * 抽奖池的大小
   *
   */
  private int limit;

  public WhoYourDaddy(int limit){
    candHeap = new HeapCreater<>(new CandidateComparator());
    daddyHeap = new HeapCreater<>(new DaddyComparator());
    customerMap = new HashMap<>();
    this.limit = limit;
  }


  /**
   * 具体操作
   *
   * @param enterTime 当前时间
   * @param customerId 用户id
   * @param buyOrRefund 购买或者退款
   */
  public void operation(int enterTime, int customerId, boolean buyOrRefund) {
    // 又是退款，又是之前不存在的直接返回，因为不会影响当时的候选池和抽奖池
    if (!buyOrRefund && !customerMap.containsKey(customerId)){
      return;
    }

    if (!customerMap.containsKey(customerId)){
      Customer customer = new Customer(customerId, 0, 0);
      customerMap.put(customerId, customer);
    }
    Customer c = customerMap.get(customerId);
    if (buyOrRefund){
      c.buy++;
    }else {
      c.buy--;
    }

    // 如果c.buy == 0, 就直接从
    if(c.buy == 0){
      customerMap.remove(customerId);
    }

    // c.buy >= 1
    if (!candHeap.contains(c) && !daddyHeap.contains(c)){
      // 两个池子里都没有
      if (daddyHeap.size() < limit){
        // 进这个的条件是， daddy没有满，并且 candHeap其实是空的，因为不存在，daddy是 < k时，candHeap里面还有数据
        c.enterTime = enterTime;
        daddyHeap.push(c);
      }else {
        // daddyHeap满足了==k, contains可以继续放
        c.enterTime = enterTime;
        candHeap.push(c);
      }
    }else if(candHeap.contains(c)){
      // 候选人池有，但抽奖池没有
     if (c.buy == 0){
       candHeap.remove(c);
     }else {
       candHeap.resign(c);
     }

    }else{
      // 候选池没有，抽奖池有
      // 两个都有不存在
      if (c.buy == 0) {
        daddyHeap.remove(c);
      }else {
        daddyHeap.resign(c);
      }
    }

    daddyMove(enterTime);
  }

  private void daddyMove(int enterTime) {
    if (candHeap.isEmpty()){
      return;
    }

    if (daddyHeap.size() < limit){
      Customer pop = candHeap.pop();
      pop.enterTime = enterTime;
      daddyHeap.push(pop);
    }else {
      if (daddyHeap.peek().buy < candHeap.peek().buy){
        Customer daddyPop = daddyHeap.pop();
        Customer candPop = candHeap.pop();
        daddyPop.enterTime = enterTime;
        candPop.enterTime = enterTime;
        daddyHeap.push(candPop);
        candHeap.push(daddyPop);
      }
    }






  }

  /**
   * 获取当前 堆中的数据
   *
   * @return
   */
  public List<Integer> getDaddies() {

    return daddyHeap.getAllElements().stream()
        .map(c -> c.id)
        .collect(Collectors.toList());
  }
}
