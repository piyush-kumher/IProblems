package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.ArrayList;
import java.util.List;

//    https://leetcode.com/problems/two-sum-iv-input-is-a-bst/solution/
public class TwoSumBinarySearchTree {

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        populate(root, list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum > k) {
                j--;
            } else if (sum < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    private void populate(TreeNode curr, List<Integer> list) {
        if (curr != null) {
            populate(curr.left, list);
            list.add(curr.val);
            populate(curr.right, list);
        }
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
