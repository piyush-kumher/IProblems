package com.piyush.psds.facebook.trees_and_graph;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/binary-tree-vertical-order-traversal/submissions/
public class VerticalOrderTraversalOfATree {

    int minDistance = 0;
    int maxDistance = 0;

    // 5%
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        Map<Integer, List<Pair<Integer, Integer>>> mem = new HashMap<>();
        dfs(root, mem, 0, 0);
        for(int i=minDistance; i <= maxDistance; i++) {
            if(mem.containsKey(i)) {
                mem.get(i).sort(Comparator.comparingInt(Pair::getKey));
                res.add(mem.get(i).stream().map(Pair::getValue).collect(Collectors.toList()));
            }
        }
        return res;
    }

    private void dfs(TreeNode node, Map<Integer, List<Pair<Integer, Integer>>> mem, int row, int column) {
        mem.putIfAbsent(column, new ArrayList<>());
        mem.get(column).add(new Pair<>(row, node.val));
        minDistance = Math.min(minDistance, column);
        maxDistance = Math.max(maxDistance, column);
        if(node.left != null) {
            dfs(node.left, mem, row+1, column-1);
        }
        if(node.right != null) {
            dfs(node.right, mem, row+1, column+1);
        }
    }


    // 89%
    public List<List<Integer>> verticalOrder_best(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        int minDistance = 0;
        int maxDistance = 0;
        Map<Integer, List<Integer>> mem = new HashMap<>();
        Queue<Pair<TreeNode, Integer>> q  = new LinkedList<>();
        q.offer(new Pair<>(root, 0));
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i < size; i++) {
                Pair<TreeNode, Integer> p = q.remove();
                TreeNode t = p.getKey();
                int distance = p.getValue();
                minDistance = Math.min(minDistance, distance);
                maxDistance = Math.max(maxDistance, distance);
                mem.putIfAbsent(distance, new ArrayList<>());
                mem.get(distance).add(t.val);
                if(t.left != null) {
                    q.offer(new Pair<>(t.left, distance-1));
                }
                if(t.right != null) {
                    q.offer(new Pair<>(t.right, distance+1));
                }
            }
        }
        for(int i=minDistance; i <= maxDistance; i++) {
            if(mem.containsKey(i)) {
                res.add(mem.get(i));
            }
        }
        return res;
    }
}
