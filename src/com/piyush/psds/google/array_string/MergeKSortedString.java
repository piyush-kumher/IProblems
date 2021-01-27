package com.piyush.psds.google.array_string;

public class MergeKSortedString {

    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        int interval = 1;
        while (interval < len) {
            for (int i = 0; (i + interval) < len; i = i + interval * 2) {
                lists[i] = mergeTwoList(lists[i], lists[i + interval]);
            }
            interval = interval * 2;
        }
        return len == 0 ? null : lists[0];
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        ListNode curr = root;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }
        return root.next;
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
