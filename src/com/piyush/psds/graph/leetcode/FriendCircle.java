package com.piyush.psds.graph.leetcode;

/**
 * https://leetcode.com/problems/friend-circles/
 */
public class FriendCircle {

    /**
     * Union Find Runtime: 2 ms, faster than 89.74% of Java online submissions for Friend Circles.
     * Memory Usage: 39.5 MB, less than 90.89% of Java online submissions for Friend Circles.
     */
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }


    /**
     * Second Approach: Using DFS Runtime: 1 ms, faster than 100.00% of Java online submissions for
     * Friend Circles. Memory Usage: 46 MB, less than 25.34% of Java online submissions for Friend
     * Circles.
     */
    public int findCircleNum_1(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int N = M.length;
        boolean[] visited = new boolean[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(i, M, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int node, int[][] M, boolean[] visited) {
        visited[node] = true;
        for (int j = 0; j < M.length; j++) {
            if (M[node][j] == 1 && !visited[j]) {
                dfs(j, M, visited);
            }
        }
    }


    class UnionFind {
        int[] parent;
        int[] rank;
        int count = 0;

        UnionFind(int m) {
            parent = new int[m];
            rank = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }
            count = m;
        }

        private int find(int p) {
            if (parent[p] == p) {
                return p;
            }
            return find(parent[p]);
        }

        private void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }
            if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }

        private int getCount() {
            return count;
        }
    }
}
