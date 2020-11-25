package com.piyush.psds.aug_6_2020_streak.leetcode.first;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode head = new ListNode(1, n2);
        ReverseLinkedList l = new ReverseLinkedList();
        ListNode revHead = l.reverseList_itr(head);
        System.out.println(revHead);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;
    }

    public ListNode reverseList_itr(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode next = head.next;
        curr.next = null;
        while (next != null) {
            ListNode t = next.next;
            next.next = curr;
            curr = next;
            next = t;
        }
        return curr;
    }

    static class ListNode {
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

}
