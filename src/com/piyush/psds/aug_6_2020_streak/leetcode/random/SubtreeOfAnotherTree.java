package com.piyush.psds.aug_6_2020_streak.leetcode.random;

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return iterate(s, t);
    }

    private boolean iterate(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        if ((s.val == t.val) && check(s, t)) {
            return true;
        }
        return iterate(s.left, t) || iterate(s.right, t);
    }

    private boolean check(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            return check(t1.left, t2.left) && check(t1.right, t2.right);
        }
        return false;
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
