package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class ReverseLinkedList2 {

    public static void main(String[] args) {
        ReverseLinkedList2 r = new ReverseLinkedList2();
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode root = new ListNode(1, node2);
        System.out.println(r.reverseBetween(root, 2, 4));
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head;
        ListNode prev = null;

        // 1 -> 2 -> 3 -> 4 -> 5 -> null
        // m = 2 , n = 4
        while (m > 1) {
            prev = curr;
            curr = curr.next;
            m--;
            n--;
        }
        //  m = 1, n = 3
        //connection: 1
        ListNode connection = prev;

        //tail: 2
        ListNode tail = curr;

        // this will run 3 times as n is 3 : 4 -> 3 -> 2
        while (n > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            n--;
        }
        // curr: 5
        // prev = 4
        if (connection != null) {
            // 1 -> 4
            connection.next = prev;
        } else {
            head = prev;
        }

        //2 -> 5
        tail.next = curr;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
