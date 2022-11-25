package com.gzy.code.leecode.sort;

import java.util.Stack;

public class RemoveNthFromEnd_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode node = head;
        while(node != null){
            stack.push(node);
            node = node.next;
        }

        int times = 0;
        ListNode next = null;
        while(!stack.isEmpty()){
            times++;
            if(times == n){
                stack.pop();
                if(!stack.isEmpty()){
                    stack.peek().next = next;
                }
            }else{
                next = stack.pop();
            }

        }

        return next;
    }

}
