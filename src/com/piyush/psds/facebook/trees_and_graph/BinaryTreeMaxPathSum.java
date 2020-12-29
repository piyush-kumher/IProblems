package com.piyush.psds.facebook.trees_and_graph;

public class BinaryTreeMaxPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        recurse(root);
        return max;
    }
    public int recurse(TreeNode node) {
        if(node == null) {
            return 0;
        }
        if(node.left == null && node.right == null) {
            max = Math.max(max, node.val);
            return node.val;
        }
        int leftMax = node.left != null ? recurse(node.left) : 0;
        int rightMax = node.right != null ? recurse(node.right) : 0;
        int chainedMax = Math.max(node.val, Math.max(node.val + leftMax, node.val + rightMax));
        max = Math.max(max, Math.max(chainedMax, leftMax + rightMax + node.val));
        return chainedMax;
    }


}
