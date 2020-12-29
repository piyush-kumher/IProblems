package com.piyush.psds.facebook.trees_and_graph;

public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        doIt(root);
    }

    private TreeNode doIt(TreeNode node) {
        if(node == null || (node.left == null && node.right == null)) {
            return node;
        }
        TreeNode leftTail = doIt(node.left);
        TreeNode rightTail = doIt(node.right);
        if(leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }
        return rightTail == null ? leftTail : rightTail;
    }

}
