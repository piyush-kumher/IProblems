package com.piyush.psds.google.trees_and_graph;

// https://leetcode.com/problems/count-complete-tree-nodes/
public class CountCompleteTreeNodes {

    public static void main(String[] args) {
        //TreeNode n7 = new TreeNode(7);
        //TreeNode n6 = new TreeNode(6);
        //TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, null, null);
        TreeNode n2 = new TreeNode(2, n4, null);
        TreeNode root = new TreeNode(1, n2, n3);
        CountCompleteTreeNodes ctn = new CountCompleteTreeNodes();
        System.out.println(ctn.countNodes(root));
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = findDepth(root);
        if (depth == 0) {
            return 1;
        }
        int l = 1;
        // A tree at depth 'd' has pow(2, d) nodes.
        int r = (int) Math.pow(2, depth) - 1;
        int pivot;
        while (l <= r) {
            pivot = l + (r - l) / 2;
            if (exists(pivot, depth, root)) {
                l = pivot + 1;
            } else {
                r = pivot - 1;
            }
        }
        // number of elements in the tree before d depth are pow(2, d) - 1
        return (int) Math.pow(2, depth) - 1 + l;
    }

    private boolean exists(int id, int d, TreeNode node) {
        int l = 0;
        int r = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; i++) {
            pivot = l + (r - l) / 2;
            if (id <= pivot) {
                r = pivot;
                node = node.left;
            } else {
                l = pivot + 1;
                node = node.right;
            }
        }
        return node != null;
    }

    private int findDepth(TreeNode node) {
        int d = 0;
        while (node.left != null) {
            node = node.left;
            ++d;
        }
        return d;
    }

}

