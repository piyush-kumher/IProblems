package com.piyush.psds.facebook.trees_and_graph;

public class DiameterOfBinaryTree {

    int max = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        doIt(root);
        return max - 1;
    }

    private int doIt(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }
        int ld = node.left != null ? doIt(node.left) : 0;
        int rd = node.right != null ? doIt(node.right) : 0;
        max = Math.max(max, 1 + ld + rd);
        return Math.max(ld + 1, rd + 1);
    }

}
