package com.piyush.psds.google.trees_and_graph;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/
 */
public class BinaryTreeMaxPathSum {


    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        recurse(root);
        return maxPath;
    }

    public int recurse(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            maxPath = Math.max(maxPath, node.val);
            return node.val;
        }
        int leftMax = node.left == null ? 0 : recurse(node.left);
        int rightMax = node.right == null ? 0 : recurse(node.right);

        int chainedMax = Math.max(node.val, Math.max(node.val + leftMax, node.val + rightMax));
        maxPath = Math.max(maxPath, Math.max(chainedMax, node.val + leftMax + rightMax));
        return chainedMax;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
