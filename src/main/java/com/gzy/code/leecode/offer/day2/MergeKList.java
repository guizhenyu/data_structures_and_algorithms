package com.gzy.code.leecode.offer.day2;

public class MergeKList {
    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表
     *
     *
     * lists[i].length 的总和不超过 10^4
     * @param lists
     * @return
     */

    public static ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        int maxIndex = 10001;
        ListNode[] nodeTailRecord = new ListNode[maxIndex * 2];
        ListNode[] nodeHeadRecord = new ListNode[maxIndex * 2];
        ListNode ans = null;
        ListNode node = null;
        ListNode tailRecord = null;
        ListNode headRecord = null;
        for (int i = 0; i < n; i++){
            node = lists[i];
            while (node != null){
                int index = node.val + maxIndex;
                tailRecord = nodeTailRecord[index];
                headRecord = nodeHeadRecord[index];
                if(headRecord == null){
                    nodeHeadRecord[index] = new ListNode(node.val, null);;
                }else {
                    if(tailRecord == null){
                        tailRecord = new ListNode(node.val, null);
                        headRecord.next = tailRecord;
                        nodeTailRecord[index] = tailRecord;
                    }else {
                        tailRecord.next = new ListNode(node.val, null);
                        nodeTailRecord[index] = tailRecord.next;
                    }
                }
                node = node.next;
            }


        }
        ListNode nodePre = null;
        for (int i = 0; i < maxIndex * 2 ; i++){
            if (nodeHeadRecord[i] != null){
                if (ans == null){
                    ans = nodeHeadRecord[i];
                }
                if (nodePre == null){
                    nodePre = nodeHeadRecord[i];
                }else {
                    nodePre.next = nodeHeadRecord[i];
                    nodePre = nodePre.next;
                }
            }
            if(nodeTailRecord[i] != null){
                nodePre = nodeTailRecord[i];
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        ListNode listNode = new ListNode(1, null);
        ListNode listNode1 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(2, null);
        ListNode listNode3 = new ListNode(3, null);
        ListNode listNode4 = new ListNode(4, null);
        ListNode listNode5 = new ListNode(4, null);
        ListNode listNode6 = new ListNode(5, null);
        ListNode listNode7 = new ListNode(6, null);
        listNode.next = listNode4;
        listNode4.next = listNode6;
        listNode1.next = listNode3;
        listNode3.next = listNode5;
        listNode2.next = listNode7;

        ListNode[] listNodes = new ListNode[3];
        listNodes[0] =listNode;
        listNodes[1] =listNode1;
        listNodes[2] =listNode2;


        ListNode ans = mergeKLists(listNodes);

        while (ans != null){
            System.out.print(ans.val + " ");
            ans = ans.next;
        }

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
