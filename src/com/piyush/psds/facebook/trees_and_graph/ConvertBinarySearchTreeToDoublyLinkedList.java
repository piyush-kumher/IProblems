package com.piyush.psds.facebook.trees_and_graph;

public class ConvertBinarySearchTreeToDoublyLinkedList {

    Node first = null;
    Node last = null;
    public Node treeToDoublyList(Node root) {
        if(root == null) {
            return null;
        }
        doIt(root);
        last.right = first;
        first.left = last;
        return first;
    }

    private void doIt(Node node) {
        if(node != null) {
            doIt(node.left);
            if(last != null) {
                last.right = node;
                node.left = last;
            } else {
                first = node;
            }
            last = node;
            doIt(node.right);
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
