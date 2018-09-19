package com.victor.LeetCode;

/**
 * Remove Linked List Elements
 *
 * 链表
 */

public class NO_203 {

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // has dummyHead
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null; // loitering Object
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    // No dummyHead
    public ListNode removeElements2(ListNode head, int val) {

//        if (head == null) return null;

        // if head need to be delete
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) return null;

        // delete node(is not head)
        ListNode prev = head;
        while(prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null; // loitering Object
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    // recursion method
    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }

//        if (head.val == val) {
//            // delete the head Node, and return the rest of the LinkedList(i.e. the next Node)
//            return removeElements3(head.next, val);
//        } else {
//            head.next = removeElements3(head.next, val);
//            return head;
//        }
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

}
