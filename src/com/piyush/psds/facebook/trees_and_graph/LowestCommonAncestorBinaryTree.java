package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

public class LowestCommonAncestorBinaryTree {

    TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        lca(root, p, q);
        return ans;
    }

    private boolean lca(TreeNode n, TreeNode p, TreeNode q) {
        if(n == null) {
            return false;
        }
        int lf = lca(n.left, p, q) ? 1 : 0;
        int rf = lca(n.right, p, q) ? 1 : 0;
        int self = ((n == p) || (n == q)) ? 1 : 0;
        if(lf + rf + self >= 2) {
            ans = n;
        }
        return (lf + rf + self) > 0;
    }

    public TreeNode lowestCommonAncestor_1(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Stack<TreeNode> stk = new Stack<>();
        parent.put(root, null);
        stk.push(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stk.pop();
            if(node.left != null) {
                parent.put(node.left, node);
                stk.push(node.left);
            }
            if(node.right != null) {
                parent.put(node.right, node);
                stk.push(node.right);
            }
        }
        Set<TreeNode> set = new HashSet<>();
        while(p != null) {
            set.add(p);
            p = parent.get(p);
        }
        while(!set.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

}
