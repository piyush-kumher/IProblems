package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        construct_paths(root, "", paths);
        return paths;
    }

    public void construct_paths(TreeNode root, String path, LinkedList<String> paths) {
        if (root != null) {
            path += Integer.toString(root.val);
            if ((root.left == null) && (root.right == null))  // if reach a leaf
                paths.add(path);  // update paths
            else {
                path += "->";  // extend the current path
                construct_paths(root.left, path, paths);
                construct_paths(root.right, path, paths);
            }
        }
    }

    public List<String> binaryTreePaths_1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            return new ArrayList<>(Arrays.asList(String.valueOf(root.val)));
        }
        List<String> res = new ArrayList<>();
        if (root.left != null) {
            for (String s : binaryTreePaths(root.left)) {
                res.add(root.val + "->" + s);
            }
        }
        if (root.right != null) {
            for (String s : binaryTreePaths(root.right)) {
                res.add(root.val + "->" + s);
            }
        }
        return res;
    }

}
