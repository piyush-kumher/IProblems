package com.piyush.psds.google.others;

import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodeAndReturnForest {

    public static void main(String[] args) {
        DeleteNodeAndReturnForest d = new DeleteNodeAndReturnForest();
        TreeNode t7 = new TreeNode(7);
        TreeNode t6 = new TreeNode(6);
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t3 = new TreeNode(3, t6, t7);
        TreeNode t2 = new TreeNode(2, t4, t5);
        TreeNode root = new TreeNode(1, t2, t3);
        int[] to_delete = {3, 5};
        System.out.println(d.delNodes(root, to_delete));

    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root == null) {
            return new ArrayList<>();
        }
        Set<Integer> toDelete = new HashSet<>();
        for(int delete : to_delete) {
            toDelete.add(delete);
        }
        List<TreeNode> ans = new ArrayList<>();
        populateMap(root, toDelete, true, ans);
        return ans;
    }

    private void populateMap(TreeNode node, Set<Integer> toDelete, boolean isRoot, List<TreeNode> ans) {
        if(node != null) {
            if(node.left != null) {
                populateMap(node.left, toDelete, toDelete.contains(node.val), ans);
                if(toDelete.contains(node.left.val)) {
                    node.left = null;
                }
            }
            if(node.right != null) {
                populateMap(node.right, toDelete, toDelete.contains(node.val), ans);
                if(toDelete.contains(node.right.val)) {
                    node.right = null;
                }
            }
            if(toDelete.contains(node.val)) {
                node.left = null;
                node.right = null;
            } else if(isRoot) {
                ans.add(node);
            }
        }
    }


    Set<Integer> to_delete_set;
    List<TreeNode> res;
    public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {
        to_delete_set = new HashSet<>();
        res = new ArrayList<>();
        for (int i : to_delete)
            to_delete_set.add(i);
        helper(root, true);
        return res;
    }

    private TreeNode helper(TreeNode node, boolean is_root) {
        if (node == null) return null;
        boolean deleted = to_delete_set.contains(node.val);
        if (is_root && !deleted) res.add(node);
        node.left = helper(node.left, deleted);
        node.right =  helper(node.right, deleted);
        return deleted ? null : node;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
