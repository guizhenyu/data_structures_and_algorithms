package com.gzy.code.leecode.LFU;

public class LFUAlgo {
    /**
     *
     * 0 <= capacity <= 104
     * 0 <= key <= 105
     * 0 <= value <= 109
     * 最多调用 2 * 105 次 get 和 put 方法
     *
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
     * 要实现这种时间复杂度，用空间换取时间
     *
     */

    int capacity, size, minFre;

    NodeLfu[] map = new NodeLfu[100001];
    NodeLfu[] freqMap = new NodeLfu[10001];


    public LFUAlgo(int capacity){
        this.capacity = capacity;
        this.size = 0;
        this.minFre = 1;
    }



    public int  get(int key){

        if(capacity <= 0){
            return -1;
        }

        NodeLfu ans = map[key];

        if(ans == null){
            return -1;
        }

        updateKey(ans);

        return ans.value;

    }

    public void put(int key, int value){

        if(capacity <= 0){
            return;
        }

        NodeLfu ans = map[key];

        if (ans == null){

            if(++size > capacity){
                NodeLfu removeNode = freqMap[minFre].pre;
                remove(removeNode);
                map[removeNode.key] = null;
                size--;
            }

            NodeLfu nodeLfu = freqMap[1];
            if(nodeLfu == null){
                freqMapHeadInit(1);
                nodeLfu = freqMap[1];
            }
            ans = new NodeLfu(key, value);
            insertNode(nodeLfu, ans);
            minFre = 1;
        }else {


           ans.value = value;
           updateKey(ans);

        }

        map[key] = ans;

    }



    /**
     * Node的count值+1;
     * @param node
     */
    private void updateKey(NodeLfu node) {
       remove(node);
       int freq = node.count;
       NodeLfu head = freqMap[freq];
       node.count += 1;
       if(head.next == head){
           // 当前频率的mp已经清空了
          freqMap[freq] = null;
          if(freq == minFre){
              minFre++;
          }
       }
       NodeLfu headNew = freqMap[node.count];
       if(headNew == null){
           freqMapHeadInit(node.count);
       }

       insertNode(freqMap[node.count], node);
    }


    public void freqMapHeadInit(int freq){
        NodeLfu head = new NodeLfu();
        head.pre = head;
        head.next = head;
        freqMap[freq] = head;

    }



    private void remove(NodeLfu node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }


    /**
     * 头插法
     * @param node
     * @param head
     */
    private void insertNode(NodeLfu head, NodeLfu node) {
       head.next.pre = node;
       node.next = head.next;
       node.pre = head;
       head.next = node;

    }
    class NodeLfu {

        int key, count, value;

        NodeLfu pre, next;

        public NodeLfu(int key, int value){
            this.key = key;
            this.value = value;
            this.count = 1;
        }

        public NodeLfu(){}



    }




}

