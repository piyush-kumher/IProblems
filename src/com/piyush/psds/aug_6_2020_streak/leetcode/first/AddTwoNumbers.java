package com.piyush.psds.aug_6_2020_streak.leetcode.first;

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

public class AddTwoNumbers {
    public static void main(String[] args) {
        AddTwoNumbers ts = new AddTwoNumbers();

        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(5, l2);
        ListNode l4 = new ListNode(8, l3);
        ListNode l5 = new ListNode(1, l4);

        ListNode l11 = new ListNode(4);
        ListNode l21 = new ListNode(6, l11);
        ListNode l31 = new ListNode(5, l21);

        final ListNode v = ts.addTwoNumbers(l5, l31, 0);
        System.out.println(v);
    }

    public int addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        int sum = 0;
        int tenPower = 0;
        while (l1 != null && l2 != null) {
            int s = l1.val + l2.val + carry;
            carry = s / 10;
            sum = (int) Math.pow(10, tenPower++) * (s % 10) + sum;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int s = l1.val + carry;
            carry = s / 10;
            sum = (int) Math.pow(10, tenPower++) * (s % 10) + sum;
            l1 = l1.next;
        }
        while (l2 != null) {
            int s = l2.val + carry;
            carry = s / 10;
            sum = (int) Math.pow(10, tenPower++) * (s % 10) + sum;
            l2 = l2.next;
        }
        if (carry > 0) {
            sum = (int) Math.pow(10, tenPower) * carry + sum;
        }
        return sum;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        if (l1 != null || l2 != null) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            return new ListNode(s % 10, addTwoNumbers(l1 == null ? null : l1.next, l2 == null ? null : l2.next, s / 10));
        }
        if (carry > 0) {
            return new ListNode(carry);
        }
        return null;
    }
}