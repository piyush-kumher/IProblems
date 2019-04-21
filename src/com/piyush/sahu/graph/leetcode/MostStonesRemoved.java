package com.piyush.sahu.graph.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
public class MostStonesRemoved {

    /**
     * Approach 1: DFS Runtime: 24 ms, faster than 52.11% of Java online submissions for Most Stones
     * Removed with Same Row or Column. Memory Usage: 45.6 MB, less than 40.48% of Java online
     * submissions for Most Stones
     */
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length <= 1) {
            return 0;
        }
        int N = stones.length;
        boolean[] visited = new boolean[N];
        int noOfComponents = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, stones, visited);
                noOfComponents++;
            }
        }
        return N - noOfComponents;
    }

    public void dfs(int stone, int[][] stones, boolean[] visited) {
        visited[stone] = true;
        for (int i = 0; i < stones.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (stones[stone][0] == stones[i][0] || stones[stone][1] == stones[i][1]) {
                dfs(i, stones, visited);
            }
        }
    }

    /**
     * Runtime: 158 ms, faster than 7.71% of Java online submissions for Most Stones Removed with Same Row or Column.
     * Memory Usage: 54 MB, less than 11.73% of Java online submissions for Most Stones Removed with Same Row or Column.
     */
    public int removeStones1(int[][] stones) {
        if(stones == null || stones.length < 2) {
            return 0;
        }
        UnionFind uf = new UnionFind();
        for(int i = 0; i < stones.length; i++) {
            uf.union(stones[i][0], ~stones[i][1]);
        }
        return stones.length - uf.getCount();
    }

    static class UnionFind {
        Map<Integer, Integer> parent;
        Map<Integer, Integer> rank;
        int count;

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
            count = 0;
        }

        public int find(int i) {
            if(!parent.containsKey(i)) {
                parent.put(i, i);
                rank.put(i, 0);
                count++;
            }
            if(parent.get(i) != i) {
                parent.put(i, find(parent.get(i)));
            }
            return parent.get(i);
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot == jRoot) {
                return;
            }

            if(rank.get(iRoot) < rank.get(jRoot)) {
                parent.put(iRoot, jRoot);
            } else if(rank.get(jRoot) < rank.get(iRoot)) {
                parent.put(jRoot, iRoot);
            } else {
                parent.put(iRoot, jRoot);
                rank.put(jRoot, rank.get(jRoot) + 1);
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }

}
