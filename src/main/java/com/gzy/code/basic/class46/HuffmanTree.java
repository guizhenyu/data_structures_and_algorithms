package com.gzy.code.basic.class46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 * description: HuffmanTree date: 2022/6/6 10:10
 *
 * 胡夫曼树：
 * 是一种把文章转化成编码结构，节省了存储和传输空间
 *
 * 实现方式：
 * 1：生成词频表
 * 2. 通过词频表，和二叉树结构，二叉树的左节点代表0，右节点代表1， 最终转化成 词 和码对应的表
 * 3. 遍历文章，根据词码表（也就是哈夫曼表）转化成哈夫曼编码
 *
 * 解码过程：
 * 哈夫曼树 是由具体的词跟nexts数组组成的，数组大小只有两个，索引下标0和1
 * 将词码表，映射到哈夫曼树上面
 * 每个词对应的码只有0和1组成，真好可以根据哈夫曼树的nexts数组的索引下标来进行匹配寻找
 * 生成哈夫曼树后
 *
 * 根据哈夫曼编码，和哈夫曼树
 * 哈夫曼编码一次遍历，但是只要在哈夫曼树上找到响应的词后，哈夫曼树就要退回到头结点继续下面的匹配，
 * 知道哈夫曼编码遍历完成
 *
 *
 *
 * @author: guizhenyu
 */
public class HuffmanTree {

  public static void main(String[] args) {
    int len = 500;
    int range = 26;
    int testTime = 100000;
    for (int i = 0; i < testTime; i++){
      int N = (int)(Math.random() * len) + 1;
      String test = generateRandomStr(N, range);
      // 获取词频表
      Map<Character, Integer> countMap = countMap(test);
      // 获取每个词对应的哈夫曼编码
      Map<Character, String> huffmanForm = huffmanForm(countMap);
      // 将字符串转化成哈夫曼表
      String encode = huffmanEncode(test, huffmanForm);
      // 将哈夫曼表转化成字符串
      String decode = huffmanDecode(encode, huffmanForm);
      if (!
          test.equals(decode)){
        System.out.println(test);
        System.out.println(encode);
        System.out.println(decode);
        System.out.println("oops!");
      }
    }
  }

  private static String huffmanDecode(String encode, Map<Character, String> huffmanForm) {
    
    TireTree root = createTireTree(huffmanForm);
    TireTree cur = root;
    StringBuilder sb = new StringBuilder();

    char[] chars = encode.toCharArray();

    for (int i = 0; i < chars.length; i++){
     // 根据001001，来跟TireTree上的树节点匹配
      int index = chars[i] == '0'? 0 : 1;
      cur = cur.nexts[index];
      if (cur.nexts[0] == null && cur.nexts[1] == null){
        sb.append(cur.value);
        cur = root;
      }

    }

    return sb.toString();
  }

  // 把 char跟001编码绑定到TireTree树结构中，0和1代表nexts数组的索引下标
  private static TireTree createTireTree(Map<Character, String> huffmanForm) {
    TireTree root = new TireTree();
    for (Character key : huffmanForm.keySet()){
      TireTree cur = root;
      // 寻找 当前key在TireTree上的位置
      char[] chars = huffmanForm.get(key).toCharArray();
      for (int i = 0; i < chars.length; i++){
        int index = chars[i] == '0'? 0 : 1;
        if (cur.nexts[index] == null){
          cur.nexts[index] = new TireTree();
        }
        cur = cur.nexts[index];
      }
      cur.value = key;
    }
    return root;
  }

  private static String huffmanEncode(String str, Map<Character, String> huffmanForm) {

    char[] chars = str.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < chars.length; i++){
      sb.append(huffmanForm.get(chars[i]));
    }
    return sb.toString();
  }

  private static Map<Character, String> huffmanForm(Map<Character, Integer> countMap) {
    Map<Character, String> ans = new HashMap<>();
    if (countMap.size() == 1){
      for (char key : countMap.keySet()){
        ans.put(key, "0");
      }
      return ans;
    }

    Map<Node, Character> nodes = new HashMap<>();
    // 小跟堆；这边利用堆来进行 count的合并
    PriorityQueue<Node> nodeHeap = new PriorityQueue<>(new NodeComp());


    // 将countMap封装成<node, character> 放入nodeMap中
    // 然后将node同时放入小根堆中
    for (Entry<Character, Integer> entry : countMap.entrySet()){
      Node node = new Node(entry.getValue());
      nodes.put(node, entry.getKey());
      nodeHeap.add(node);
    }

    // 每次从堆中弹出两个最小的node, 合并成一个node，再放入小根堆中，直到最后堆中只剩一个node，也就是哈夫曼树的头结点
    while (nodeHeap.size() != 1){
      Node a = nodeHeap.poll();
      Node b = nodeHeap.poll();
      Node node = new Node(a.count + b.count);
      node.left = a;
      node.right = b;
      nodeHeap.add(node);
    }

    Node head = nodeHeap.poll();
    fillHuffman(head, "", nodes, ans);
    return ans;
  }

  private static void fillHuffman(Node head, String pre, Map<Node, Character> nodes, Map<Character, String> ans) {
    if (nodes.containsKey(head)){
      ans.put(nodes.get(head), pre);
    }else {
      fillHuffman(head.left, pre + '0', nodes, ans);
      fillHuffman(head.right, pre + '1', nodes, ans);
    }
  }

  private static String generateRandomStr(int n, int range) {

    char[] chars = new char[n];

    for (int i = 0; i < n; i++){
      chars[i] = (char)((int)(Math.random() * range) + 'a');
    }

    return String.valueOf(chars);
  }

  private static Map<Character, Integer> countMap(String str) {
    char[] chars = str.toCharArray();
    Map<Character, Integer> countMap = new HashMap<>();
    for (char ch : chars){
      if (countMap.containsKey(ch)){
        countMap.put(ch, countMap.get(ch) + 1);
      }else {
        countMap.put(ch, 1);
      }
    }

    return countMap;
  }

  public static class Node{
    int count;
    Node left;
    Node right;
    public Node(int count){
      this.count = count;
    }
  }

  public static class NodeComp implements Comparator<Node> {


    @Override
    public int compare(Node o1, Node o2) {
      return o1.count - o2.count;
    }
  }


  public static class TireTree{
    char value;
    TireTree[] nexts;
    
    public TireTree(){
      value = 0;
      nexts = new TireTree[2];
    }
  }

}
