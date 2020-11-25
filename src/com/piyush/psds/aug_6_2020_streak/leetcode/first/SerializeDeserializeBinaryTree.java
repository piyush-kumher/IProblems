package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree t = new SerializeDeserializeBinaryTree();
        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n4, n5);
        TreeNode n2 = new TreeNode(2);
        TreeNode root = new TreeNode(1, n2, n3);
        String ser = t.serialize(root);
        TreeNode des = t.deserialize(ser);
        System.out.println(ser);
        System.out.println(des);
    }

    //Exceeds the memoty limit
    public String serialize_1(TreeNode root) {
        int h = getHeight(root);
        int maxEle = maxElement(h - 1);
        char[] arr = new char[maxEle];
        Arrays.fill(arr, '-');
        populateArr(root, arr, 0);
        return new String(arr);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize_1(String data) {
        int maxLength = data.length();
        return doIt(data, maxLength, 0);
    }


    public String serialize(TreeNode root) {
        return reserialize(root, "");
    }

    private String reserialize(TreeNode ele, String res) {
        if (ele == null) {
            res += "null,";
        } else {
            res += ele.val + ",";
            res = reserialize(ele.left, res);
            res = reserialize(ele.right, res);
        }
        return res;
    }

    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    public TreeNode rdeserialize(List<String> l) {
        // Recursive deserialization.
        if (l.get(0).equals("null")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

    private void populateArr(TreeNode ele, char[] arr, int n) {
        if (ele == null) {
            return;
        }
        arr[n] = (char) (ele.val + 48);
        populateArr(ele.left, arr, 2 * n + 1);
        populateArr(ele.right, arr, 2 * n + 2);
    }

    private TreeNode doIt(String data, int maxLength, int i) {
        if (i >= maxLength || data.charAt(i) == '-') {
            return null;
        }
        int val = data.charAt(i) - 48;
        TreeNode n = new TreeNode(val);
        n.left = doIt(data, maxLength, 2 * i + 1);
        n.right = doIt(data, maxLength, 2 * i + 2);
        return n;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lDepth = getHeight(root.left);
        int rDepth = getHeight(root.right);
        return 1 + Math.max(lDepth, rDepth);
    }

    private int maxElement(int height) {
        return (int) Math.pow(2, height + 1) - 1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
