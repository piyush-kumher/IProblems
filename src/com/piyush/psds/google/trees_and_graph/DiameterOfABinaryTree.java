package com.piyush.psds.google.trees_and_graph;


// https://leetcode.com/problems/diameter-of-binary-tree/
public class DiameterOfABinaryTree {

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        recurse(root);
        return max;
    }

    private int recurse(TreeNode node) {
        if(node.left == null && node.right == null) {
            return 0;
        }
        int nl = 0;
        if(node.left != null) {
            nl = 1 + recurse(node.left);
        }
        int nr = 0;
        if(node.right != null) {
            nr = 1 + recurse(node.right);
        }
        max = Math.max(max, nl + nr);
        return Math.max(nl, nr);
    }

}
