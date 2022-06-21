package com.gzy.code.basic.class07HeapShow;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * description: EveryStepShowBoss date: 2022/1/5 2:14 下午
 *
 * 求每个时间点的，抽奖用户的列表
 *
 *
 *
 * @author: guizhenyu
 */
public class EveryStepShowBoss {

  public static class Customer{

    /**
     * 客户的id
     *
     */
    public int id;

    /**
     * 客户的购买数量
     * 负数代表退款
     *
     */
    public int buy;

    /**
     * 进入候选池 或者 抽奖池的 时间点，方便排列
     *
     */
    public int enterTime;

    public Customer(int v, int b, int enterTime){
      id = v;
      buy = b;
      enterTime = enterTime;
    }
  }


  public static class Data{

    /**
     *  arr:
     *    下标表示时间点
     *    具体的金额表示消费金额
     *  op:
     *    表示是买商品还是退款
     */
    public int[] arr;
    public boolean[] op;

    public Data(int[] a, boolean[] o){
      arr = a;
      op = o;
    }
  }

  public static Data randomData(int maxValue, int maxLen){
    int len = (int)(Math.random() * maxLen) + 1;
    int[] arr = new int[len];
    boolean[] op = new boolean[len];
    for(int i = 0; i < len; i++){
      arr[i] = (int)(Math.random() * maxValue );
      op[i] = Math.random() > 0.5 ? true : false;
    }

    return new Data(arr, op);
  }

  public static void main(String[] args) {

    int testTime = 100000;

    int maxSize = 100;

    int maxValue = 10;

    int maxTop = 6;

    System.out.println("test started!");
    for (int i = 0; i < testTime; i++){
      Data data = randomData(maxValue, maxSize);
      int k = (int)(Math.random() * maxTop) + 1;
      int[] arr = data.arr;
      boolean[] op = data.op;
      List<List<Integer>>  ans1= topK(arr, op, k);
      List<List<Integer>>  ans2= compare(arr, op, k);

      if (!sameAns(ans1, ans2)){

        for (int j = 0; j < arr.length; j++){
          System.out.println(arr[j] + " , " + op[j]);
        }

        System.out.println(k);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println("Fucking fucked!");

        break;
      }

    }

    System.out.println("test finished!");

  }

  private static boolean sameAns(List<List<Integer>> ans1, List<List<Integer>> ans2) {

    if (ans1.size() != ans2.size()){
      return false;
    }

    for (int i = 0; i < ans1.size(); i++){
      if (ans1.get(i).size() != ans2.get(i).size()){
        return false;
      }
      List<Integer> list1 = ans1.get(i);
      List<Integer> list2 = ans2.get(i);
      list1.sort((a, b) -> a - b);
      list2.sort((a, b) -> a - b);
      for (int j = 0; j < list1.size(); j++){
        if (!list1.get(j).equals(list2.get(j))){
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 最原始的方法 humanWisdom
   *
   * @param arr
   * @param k
   * @return
   */
  private static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    // 标记客户目前状态
    HashMap<Integer, Customer> map = new HashMap<>();
    // 用于存放待选用户
    List<Customer> cands = new ArrayList<>();

    // 用于存放当前时间点的抽奖池用户
    List<Customer> daddy = new ArrayList<>();

    for (int i = 0; i < arr.length; i++){

      int id = arr[i];

      boolean buyOrRefund = op[i];

      if (!buyOrRefund && !map.containsKey(id)){
        // 是退款，并且客户的有效记录没有，直接返回当前的抽奖池
        ans.add(getCurAns(daddy));
        continue;
      }

      //没有发生： 用户购买数为0并且又退货了
      //用户之前购买的数是0，此时购买
      //用户之前购买 > 0， 此时购买
      // > 0, 此时退货
      if(!map.containsKey(id)){
        // 能进这个的前提是，本次是购买事件，

        map.put(id, new Customer(id, 0, 0));
      }

      // 能到这边只有两种情况
      // 是退款事件， 但是 customer.buy >=1
      // 购买事件
      Customer customer = map.get(id);
      if (!buyOrRefund){
        customer.buy--;
      }else {
        customer.buy++;
      }

      if (customer.buy == 0){
        map.remove(id);
      }

      // 此时 customer 怎么处理
      // 第一次出现，那就判断一下，要不就是放 candas 要不就是放daddy中
      if (!cands.contains(customer) && !daddy.contains(customer)){
        if (daddy.size() < k){
          customer.enterTime = i;
          daddy.add(customer);
        }else {
          customer.enterTime = i;
          cands.add(customer);
        }

      }


      // 为0的抽奖池和候选人都删除
      cleanZeroBuy(cands);
      cleanZeroBuy(daddy);

      cands.sort(new CandidateComparator());
      daddy.sort(new DaddyComparator());

      // 从候选人中挑选进抽奖池
      move(cands, daddy, k, i);
      ans.add(getCurAns(daddy));
    }

    return ans;
  }

  private static void move(List<Customer> cands, List<Customer> daddy, int k, int enterTime) {
    // cands 是倒序
    // daddy是升序
    if (cands.isEmpty()){
      return;
    }
    // 抽奖池还没有满
    if (daddy.size() < k) {
      // 这边为什么只从候选池中选一个，
      // 首先第一个是最大的
      // 其次， 由于遍历每次只发生一个客户的变化，所以只会对一个候选人产生影响
      Customer customer = cands.get(0);
      customer.enterTime = enterTime;
      daddy.add(customer);
      cands.remove(0);
    }else {
      // 进入这个条件： 抽奖池满了，并且候选池不为空
      // 只需要判断 抽奖池和候选池的第一个人的购买数
      if (daddy.get(0).buy < cands.get(0).buy){
        Customer candCust = cands.get(0);
        Customer daddyCust = daddy.get(0);
        daddy.remove(0);
        cands.remove(0);

        candCust.enterTime = enterTime;
        daddyCust.enterTime = enterTime;

        daddy.add(candCust);

        cands.add(daddyCust);

      }

    }

  }

  /**
   *
   * 这边是实现候选人的比较算法，
   * 候选人要求是倒序
   */
  public static class CandidateComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer o1, Customer o2) {
      return o2.buy != o1.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
    }
  }

  /**
   * 抽奖池中人的比较算法
   * 是顺序
   *
   */
  public static class DaddyComparator implements Comparator<Customer>{

    @Override
    public int compare(Customer o1, Customer o2) {
      return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
    }
  }

  private static void cleanZeroBuy(List<Customer> customers) {

    List<Customer> zeroNoCus = new ArrayList<>();

    for (Customer c : customers){
      if (c.buy != 0){
        zeroNoCus.add(c);
      }
    }

    customers.clear();

    for (Customer c : zeroNoCus) {
      customers.add(c);
    }
  }

  private static List<Integer> getCurAns(List<Customer> daddy) {

    List<Integer> ans = new ArrayList<>();
    for (Customer c : daddy){
      ans.add(c.id);
    }

    return ans;
  }


  /**
   *  用堆实现该功能
   *
   * @param arr
   * @param op
   * @param k
   * @return
   */
  private static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
    List<List<Integer>> ans = new ArrayList<>();
    WhosYourDaddy1 whoYourDaddy = new WhosYourDaddy1(k);

    for (int i = 0 ; i < arr.length; i++){
      whoYourDaddy.operation(i, arr[i], op[i]);
      ans.add(whoYourDaddy.getDaddies());
    }

    return ans;
  }

  public static class WhosYourDaddy1 {
    private HashMap<Integer, Customer> customers;
    private HeapCreater<Customer> candHeap;
    private HeapCreater<Customer> daddyHeap;
    private final int daddyLimit;

    public WhosYourDaddy1(int limit) {
      customers = new HashMap<Integer, Customer>();
      candHeap = new HeapCreater<>(new CandidateComparator());
      daddyHeap = new HeapCreater<>(new DaddyComparator());
      daddyLimit = limit;
    }

    // 当前处理i号事件，arr[i] -> id,  buyOrRefund
    public void operation(int time, int id, boolean buyOrRefund) {
      if (!buyOrRefund && !customers.containsKey(id)) {
        return;
      }
      if (!customers.containsKey(id)) {
        customers.put(id, new Customer(id, 0, 0));
      }
      Customer c = customers.get(id);
      if (buyOrRefund) {
        c.buy++;
      } else {
        c.buy--;
      }
      if (c.buy == 0) {
        customers.remove(id);
      }
      if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
        if (daddyHeap.size() < daddyLimit) {
          c.enterTime = time;
          daddyHeap.push(c);
        } else {
          c.enterTime = time;
          candHeap.push(c);
        }
      } else if (candHeap.contains(c)) {
        if (c.buy == 0) {
          candHeap.remove(c);
        } else {
          candHeap.resign(c);
        }
      } else {
        if (c.buy == 0) {
          daddyHeap.remove(c);
        } else {
          daddyHeap.resign(c);
        }
      }
      daddyMove(time);
    }

    public List<Integer> getDaddies() {
      List<Customer> customers = daddyHeap.getAllElements();
      List<Integer> ans = new ArrayList<>();
      for (Customer c : customers) {
        ans.add(c.id);
      }
      return ans;
    }

    private void daddyMove(int time) {
      if (candHeap.isEmpty()) {
        return;
      }
      if (daddyHeap.size() < daddyLimit) {
        Customer p = candHeap.pop();
        p.enterTime = time;
        daddyHeap.push(p);
      } else {
        try {
          if (candHeap.peek().buy > daddyHeap.peek().buy) {
            Customer oldDaddy = daddyHeap.pop();
            Customer newDaddy = candHeap.pop();
            oldDaddy.enterTime = time;
            newDaddy.enterTime = time;
            daddyHeap.push(newDaddy);
            candHeap.push(oldDaddy);
          }
        } catch (Exception e){
          e.printStackTrace();
          System.out.println("fdgfdg");
          throw e;
        }
      }
    }

  }


}
