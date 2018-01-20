package com.piyush.sahu.tree.bst;

/**
 * https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree?h_r=next-challenge&h_v=zen
 */
public class TreeHeight {

    static int height(Node root) {
        if(root == null){
            return 0;
        }
        return actualHeight(root) -1;
    }

    static int actualHeight(Node node){
        if(node != null){
            int leftHeight = 1 + actualHeight(node.left);
            int rightHeight = 1 + actualHeight(node.right);
            return leftHeight > rightHeight ? leftHeight : rightHeight;
        }
        return 0;
    }

}
