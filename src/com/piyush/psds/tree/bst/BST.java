package com.piyush.psds.tree.bst;

public class BST {

    Node root;

    public void insert1(int data){
        if(root == null){
            root = new Node(data);
        }
        else{
            Node currentNode = root;
            Node node = new Node(data);
            Node parent;
            while(true){
                parent = currentNode;
                if(data < currentNode.data){
                    currentNode = currentNode.left;
                    if(currentNode == null){
                        parent.left = node;
                        return;
                    }
                }else{
                    currentNode = currentNode.right;
                    if(currentNode == null){
                        parent.right = node;
                        return;
                    }
                }
            }
        }
    }

    public void insert(int data){
        Node newNode = new Node(data);
        root = insert(root, newNode);
    }

    private Node insert(Node node, Node newNode) {
        if(node == null){
            node = newNode;
        }else{
            if(newNode.data < node.data){
                node.left = insert(node.left, newNode);
            }else{
                node.right = insert(node.right, newNode);
            }
        }
        return node;
    }

    public Node search(int data){
        return search(root, data);
    }

    private Node search(Node node, int data) {
        if(node == null){
            return node;
        }else if(node.data == data){
            return node;
        }else{
            if(data < node.data){
                return search(node.left, data);
            }else{
                return search(node.right, data);
            }
        }
    }

}
