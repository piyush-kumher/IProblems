package com.piyush.psds.tree.geeksforgeeks;


import java.util.ArrayList;
import java.util.List;

class Node{
    Integer node;
    Integer height;
    List<Node> children;
    public Node(int node){
        this.node = node;
        this.children = new ArrayList<>();
    }
    public void addChild(Node n){
        this.children.add(n);
    }
}

public class InformationPassingMinInformation {

    public Integer findMinInteration(Node node){
        if(node.children.size() == 0){
            node.height = 0;
            return 0;
        }
        int maxHeight = 0;
        int maxIterations = 0;
        for(Node child : node.children){
            int itr = findMinInteration(child);
            if(itr > maxIterations){
                maxIterations = itr;
            }
            if(child.height > maxHeight){
                maxHeight = child.height;
            }
        }
        node.height = maxHeight + 1;
        if(node.height == 1){
            return node.children.size();
        }else{
            return maxIterations + 1;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        root.addChild(node2);
        root.addChild(node3);
        node2.addChild(node4);
        node2.addChild(node5);
        node5.addChild(node6);
        node5.addChild(node7);
        node3.addChild(node8);
        node3.addChild(node9);
        InformationPassingMinInformation i = new InformationPassingMinInformation();
        System.out.println(i.findMinInteration(root));
    }

}
