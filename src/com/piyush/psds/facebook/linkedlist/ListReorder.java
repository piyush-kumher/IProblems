package com.piyush.psds.facebook.linkedlist;


public class ListReorder {

    public static void main(String[] args) {
        ListReorder lr = new ListReorder();
        ListNode l6 = new ListNode(6);
        ListNode l5 = new ListNode(5);
        ListNode l4 = new ListNode(4, l5);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode root = new ListNode(1, l2);
        lr.reorderList(root);
        System.out.println(root.val);
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // find the middle node
        ListNode middle = findMiddle(head);
        ListNode hr = reverse(middle);
        mergeBoth(head, hr);
    }

    private void mergeBoth(ListNode first, ListNode second) {
        while (second.next != null) {
            ListNode t = first.next;
            first.next = second;
            first = t;

            t = second.next;
            second.next = first;
            second = t;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode t = node;
            node = node.next;
            t.next = prev;
            prev = t;
        }
        return prev;
    }

    private ListNode findMiddle(ListNode node) {
        ListNode slow = node;
        ListNode fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
