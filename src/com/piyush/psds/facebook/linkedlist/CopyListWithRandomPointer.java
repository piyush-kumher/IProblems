package com.piyush.psds.facebook.linkedlist;

import java.util.HashMap;
import java.util.Map;

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

public class CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }
        Map<Node, Node> prevToNewNode = new HashMap<>();
        Node root = new Node(head.val);
        prevToNewNode.put(head, root);
        Node prev = root;
        Node curr = head.next;
        while(curr != null) {
            Node newNode = new Node(curr.val);
            prev.next = newNode;
            prevToNewNode.put(curr, newNode);
            prev = newNode;
            curr  = curr.next;
        }
        Node oldCurr = head;
        Node newCurr = root;
        while(oldCurr != null) {
            if(oldCurr.random != null) {
                newCurr.random = prevToNewNode.get(oldCurr.random);
            }
            oldCurr = oldCurr.next;
            newCurr = newCurr.next;
        }
        return root;
    }

}
