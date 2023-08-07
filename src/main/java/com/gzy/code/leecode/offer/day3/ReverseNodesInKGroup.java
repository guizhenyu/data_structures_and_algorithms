package com.gzy.code.leecode.offer.day3;


public class ReverseNodesInKGroup {
    /**
     * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }

        if (len < 2){
            return head;
        }

        int times = len / k;
        cur = head;
        ListNode preTail = null;

        while (times > 0) {
            int i = k;
            ListNode next = cur.next;

            while (i > 1) {
                ListNode nextNext = next.next;
                next.next = cur;
                cur = next;
                next = nextNext;
                i--;
            }

            if (preTail == null) {
                head.next = next;
                preTail = head;
                head = cur;
                cur = next;
            } else {
//                cur = next;
//                preTail = preTail.next;
//                preTail.next = next;
//                preTail.next = cur;
                ListNode nextTail = preTail.next;
                preTail.next.next = next;
                preTail.next = cur;
                cur = next;
                preTail = nextTail;
            }
            times--;
        }
        if (cur != null){
            preTail.next = cur;
        }

        return head;
    }

    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1, null);
        ListNode listNode2 = new ListNode(2, null);
        ListNode listNode3 = new ListNode(3, null);
        ListNode listNode4 = new ListNode(4, null);
        ListNode listNode6 = new ListNode(5, null);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode6;
//        listNode2.next = listNode7;

        ListNode ans = reverseKGroup(listNode1, 2);

        while (ans != null){

            System.out.print(ans.val + "  ");
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