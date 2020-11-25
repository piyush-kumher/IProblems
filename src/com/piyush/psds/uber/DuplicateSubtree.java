package com.piyush.psds.uber;

import java.util.*;

public class DuplicateSubtree {

    Map<String, Integer> count = new HashMap<>();
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        find(root);
        return result;
    }

    private String find(TreeNode node) {
        if (node == null) {
            return "#";
        }
        String nodeId = node.val + "," + find(node.left) + "," + find(node.right);
        count.put(nodeId, count.getOrDefault(nodeId, 0) + 1);
        if (count.get(nodeId) == 2) {
            result.add(node);
        }
        return nodeId;
    }


    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count1;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees1(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count1 = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x-> t++);
        count1.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count1.get(uid) == 2)
            ans.add(node);
        return uid;
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
