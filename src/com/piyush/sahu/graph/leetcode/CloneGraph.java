package com.piyush.sahu.graph.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/
 */

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}
    public Node(int _val) {
        this.val = _val;
    }
    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

public class CloneGraph {

    public Node cloneGraph(Node node) {
        Node newNode = new Node();
        newNode.val = node.val;
        Map<Integer, Node> map= new HashMap<>();
        map.put(newNode.val, newNode);
        cloneNode(node, newNode, map);
        return newNode;
    }

    private void cloneNode(Node node, Node newNode, Map<Integer, Node> map){
        List<Node> nextNodes = new ArrayList();
        for(Node nextNode : node.neighbors){
            if(map.containsKey(nextNode.val)){
                nextNodes.add(map.get(nextNode.val));
            }else{
                Node newNextNode = new Node();
                newNextNode.val = nextNode.val;
                map.put(nextNode.val, newNextNode);
                cloneNode(nextNode, newNextNode, map);
                nextNodes.add(newNextNode);
            }
        }
        newNode.neighbors = nextNodes;
    }

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        List<Node> node1List = new ArrayList<Node>(){{add(node2); add(node4);}};
        List<Node> node2List = new ArrayList<Node>(){{add(node1); add(node3);}};
        List<Node> node3List = new ArrayList<Node>(){{add(node2); add(node4);}};
        List<Node> node4List = new ArrayList<Node>(){{add(node1); add(node3);}};
        node1.neighbors = node1List;
        node2.neighbors = node2List;
        node3.neighbors = node3List;
        node4.neighbors = node4List;
        Node newNode = cg.cloneGraph(node1);
        System.out.println(newNode);
    }

}
