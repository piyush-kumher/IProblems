package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors.addAll(Arrays.asList(n2, n4));
        n2.neighbors.addAll(Arrays.asList(n1, n3));
        n3.neighbors.addAll(Arrays.asList(n2, n4));
        n4.neighbors.addAll(Arrays.asList(n1, n3));
        System.out.println(cg.cloneGraph(n1));
    }

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        Map<Integer, Node> mem = new HashMap<>();
        Node newNode = new Node(node.val);
        mem.put(node.val, newNode);
        cloneNode(node, newNode, mem);
        return newNode;
    }

    private void cloneNode(Node n, Node nn, Map<Integer, Node> mem) {
        List<Node> nextNodes = new ArrayList<>();
        for(Node next : n.neighbors) {
            if(mem.containsKey(next.val)) {
                nextNodes.add(mem.get(next.val));
            } else {
                Node nnn = new Node(next.val);
                mem.put(nnn.val, nnn);
                cloneNode(next, nnn, mem);
                nextNodes.add(nnn);
            }
        }
        nn.neighbors.addAll(nextNodes);
    }

    // 25ms : 89.77%
    public Node cloneGraph_1(Node node) {
        if(node == null) {
            return null;
        }
        Map<Integer, Node> mem = new HashMap<>();
        Node newNode = new Node(node.val);
        mem.put(node.val, newNode);
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            Node on = q.remove();
            Node nn = mem.get(on.val);
            for(Node next : on.neighbors) {
                Node nnn;
                if(mem.containsKey(next.val)) {
                    nnn = mem.get(next.val);
                } else {
                    nnn = new Node(next.val);
                    mem.put(nnn.val, nnn);
                    q.add(next);
                }
                nn.neighbors.add(nnn);
            }
        }
        return newNode;
    }

}
