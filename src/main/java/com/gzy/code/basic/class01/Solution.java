//package com.gzy.code.basic.class01;
//
////给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
////
////
////
//// 示例 1：
////
////
////输入：head = [1,2,3,4,5], n = 2
////输出：[1,2,3,5]
////
////
//// 示例 2：
////
////
////输入：head = [1], n = 1
////输出：[]
////
////
//// 示例 3：
////
////
////输入：head = [1,2], n = 1
////输出：[1]
////
////
////
////
//// 提示：
////
////
//// 链表中结点的数目为 sz
//// 1 <= sz <= 30
//// 0 <= Node.val <= 100
//// 1 <= n <= sz
////
////
////
////
//// 进阶：你能尝试使用一趟扫描实现吗？
//// Related Topics 链表 双指针
//// 👍 1715 👎 0
//
//
////leetcode submit region begin(Prohibit modification and deletion)
///**
// * Definition for singly-linked list.
// * public class ListNode {
// *     int val;
// *     ListNode next;
// *     ListNode() {}
// *     ListNode(int val) { this.val = val; }
// *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// * }
// */
//class Solution {
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//
//        int size = 0;
//
//        ListNode pre = null;
//        ListNode next = null;
//
//        while (null != head.next){
//
//            if (size == n + 1){
//                pre = head;
//                next = head.next.next;
//            }
//
//            if (size > n + 1){
//                pre = pre.next;
//                next = next.next;
//            }
//            size ++;
//        }
//
//        if (size > n){
//            pre.next = next;
//            return head;
//        }
//
//        if (size == n){
//            return head.next;
//        }
//
//        return head;
//
//
//    }
//}
////leetcode submit region end(Prohibit modification and deletion)
