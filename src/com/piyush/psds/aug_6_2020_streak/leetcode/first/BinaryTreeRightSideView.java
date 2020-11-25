package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/submissions/
 */
public class BinaryTreeRightSideView {


    public List<Integer> rightSideView_1(TreeNode root) {
        int depth = findDepth(root);
        Integer[] arr = new Integer[depth];
        populateDepthArray(root, arr, 0);
        return Arrays.asList(arr);
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (i == size - 1) {
                    res.add(curr.val);
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
        return res;
    }

    private void populateDepthArray(TreeNode ele, Integer[] arr, int depth) {
        if (ele == null) {
            return;
        }
        arr[depth] = ele.val;
        populateDepthArray(ele.left, arr, depth + 1);
        populateDepthArray(ele.right, arr, depth + 1);
    }

    private int findDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(findDepth(root.left), findDepth(root.right)) + 1;
    }

    static class TreeNode {
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
