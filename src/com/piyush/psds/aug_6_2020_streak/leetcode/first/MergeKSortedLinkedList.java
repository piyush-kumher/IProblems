package com.piyush.psds.aug_6_2020_streak.leetcode.first;


import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLinkedList {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        ListNode head = new ListNode(0);
        ListNode tail = head;
        for (ListNode l : lists) {
            if (l != null) {
                q.add(l);
            }
        }
        while (!q.isEmpty()) {
            ListNode l = q.remove();
            tail.next = new ListNode(l.val);
            tail = tail.next;
            l = l.next;
            if (l != null) {
                q.add(l);
            }
        }
        return head.next;
    }

    public ListNode mergeKLists_2(ListNode[] lists) {
        int interval = 1;
        while (interval < lists.length) {
            for (int i = 0; i < lists.length - interval; i += interval * 2) {
                lists[i] = mergeKLists(lists[i], lists[i + interval]);
            }
            interval = interval * 2;
        }
        return lists.length < 1 ? null : lists[0];
    }

    private ListNode mergeKLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                node.next = l1;
                node = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return head.next;
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

}
