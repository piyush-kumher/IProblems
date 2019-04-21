package com.piyush.sahu.tree.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * https://leetcode.com/problems/symmetric-tree/submissions/
 */
public class SymmetricTree {

    /**
     * Runtime: 1 ms, faster than 87.19% of Java online submissions for Symmetric Tree.
     * Memory Usage: 38.3 MB, less than 49.59% of Java online submissions for Symmetric
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Queue<TreeNode> newQueue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            while(!queue.isEmpty()){
                TreeNode node = queue.remove();
                if (node.left != null) {
                    newQueue.add(node.left);
                    list.add(node.left.val);
                } else {
                    list.add(null);
                }
                if (node.right != null) {
                    newQueue.add(node.right);
                    list.add(node.right.val);
                } else {
                    list.add(null);
                }
            }
            queue = newQueue;
            int length = list.size();
            int i = 0, j = length - 1;
            while (i < j) {
                if (list.get(i) != list.get(j)) {
                    return false;
                }
                i++; j--;
            }
        }
        return true;
    }


    /**
     * Runtime: 1 ms, faster than 87.19% of Java online submissions for Symmetric Tree.
     * Memory Usage: 38.1 MB, less than 62.34% of Java online submissions for Symmetric Tree.
     */
    public boolean isSymmetricSimplified(TreeNode root) {
        if(root == null){
            return false;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
     * Memory Usage: 39.3 MB, less than 20.95% of Java online submissions for Symmetric Tree.
     */
    public boolean isSymmetricSimple(TreeNode root) {
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2){
        if(t1 == null && t2 == null){
            return true;
        }
        if(t1 == null || t2 == null){
            return false;
        }
        return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        SymmetricTree st = new SymmetricTree();
        System.out.println(st.isSymmetricSimple(t1));
    }

}
