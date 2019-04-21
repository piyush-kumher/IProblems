package com.piyush.sahu.graph.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-height-trees/ https://www.geeksforgeeks.org/roots-tree-gives-minimum-height/
 */
public class MinimumHeightGraphTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        List<Set<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }
        int[] degree = new int[n];
        for (int i = 0; i < edges.length; i++) {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                leaves.add(i);
            }
        }
        int count = n;
        while (count > 2) {
            count = count - leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int node : leaves) {
                for (int i : adjList.get(node)) {
                    if (--degree[i] == 1) {
                        newLeaves.add(i);
                    }
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}
