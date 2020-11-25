package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

//https://leetcode.com/problems/copy-list-with-random-pointer/submissions/
public class CopyRandomList {

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        List<Node> l = new ArrayList<>();
        Node curr = head;
        int count = 0;
        Map<Node, Integer> position = new HashMap<>();
        while(curr != null) {
            l.add(new Node(curr.val));
            position.put(curr, count);
            curr = curr.next;
            count++;
        }
        curr = head;
        int currIdx = 0;
        while(curr != null) {
            Node n = l.get(currIdx);
            n.next = currIdx+1 < l.size() ? l.get(currIdx+1) : null;
            n.random = curr.random == null ? null : l.get(position.get(curr.random));
            curr = curr.next;
            currIdx++;
        }
        return l.get(0);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
