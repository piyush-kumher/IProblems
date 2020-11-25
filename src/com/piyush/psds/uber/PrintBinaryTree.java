package com.piyush.psds.uber;

import java.util.*;

public class PrintBinaryTree {

    public List<List<String>> printTree(TreeNode root) {
        int h = getHeight(root);
        String[][] arr = new String[h][(int) Math.pow(2, h) - 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = "";
            }
        }
        fillDetails(root, arr, 0, 0, arr[0].length);
        List<List<String>> ans = new ArrayList<>();
        for (String[] sr : arr) {
            ans.add(Arrays.asList(sr));
        }
        return ans;
    }

    private void fillDetails(TreeNode treeNode, String[][] arr, int i, int l, int r) {
        arr[i][(l + r) / 2] = "" + treeNode.val;
        if (treeNode.left != null) {
            fillDetails(treeNode.left, arr, i + 1, l, (l + r) / 2);
        }
        if (treeNode.right != null) {
            fillDetails(treeNode.right, arr, i + 1, (l + r + 1) / 2, r);
        }
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public class TreeNode {
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
