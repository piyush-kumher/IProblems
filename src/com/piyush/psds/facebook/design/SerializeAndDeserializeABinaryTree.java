package com.piyush.psds.facebook.design;

import java.util.Arrays;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
}

public class SerializeAndDeserializeABinaryTree {

    public static void main(String[] args) {
        SerializeAndDeserializeABinaryTree s = new SerializeAndDeserializeABinaryTree();
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3, n4, n5);
        TreeNode n2 = new TreeNode(2);
        TreeNode root = new TreeNode(1, n2, n3);
        String sel = s.serialize(root);
        System.out.println(sel);
        TreeNode del = s.deserialize(sel);
        System.out.println(del);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.substring(0, sb.length() - 1);
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        serialize(node.left, sb);
        serialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.trim().length() == 0) {
            return null;
        }
        List<String> dp = Arrays.asList(data.split(","));
        return deserialise(dp);
    }

    private TreeNode deserialise(List<String> dp) {
        String val = dp.get(0);
        dp.remove(0);
        if (val.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        if (!dp.isEmpty()) {
            root.left = deserialise(dp);
        }
        if (!dp.isEmpty()) {
            root.right = deserialise(dp);
        }
        return root;
    }

}
