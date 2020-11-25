package com.piyush.psds.uber;

import java.util.*;

public class SerializeAndDeSerNArray {

    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        String s = sb.toString();
        System.out.println(s);
        return s;
    }

    private void serialize(Node node, StringBuilder s) {
        if(node == null) {
            s.append("null").append(",");
        } else {
            s.append(node.val).append(",");
            s.append(node == null ? 0 : node.children.size()).append(",");
            for(Node child : node.children) {
                serialize(child, s);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Queue<String> q = new LinkedList<>();
        q.addAll(Arrays.asList(data.split(",")));
        return deserialize(q);
    }

    private Node deserialize(Queue<String> q) {
        String ele = q.poll();
        if("null".equals(ele)) {
            return null;
        }
        int size = Integer.parseInt(q.poll());
        Node root = new Node(Integer.parseInt(ele));
        List<Node> children = new ArrayList<>();
        for(int i=0; i < size; i++) {
            children.add(deserialize(q));
        }
        root.children = children;
        return root;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
