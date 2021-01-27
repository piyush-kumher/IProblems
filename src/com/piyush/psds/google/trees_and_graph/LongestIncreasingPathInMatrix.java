package com.piyush.psds.google.trees_and_graph;

// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/submissions/

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingPathInMatrix {

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix l = new LongestIncreasingPathInMatrix();
        int[][] arr = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(l.longestIncreasingPath(arr));
    }

    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        int[][] mem = new int[matrix.length][matrix[0].length];;
        int max = 0;
        for(int i=0; i < matrix.length; i++) {
            for(int j=0; j < matrix[0].length; j++) {
                int len = dfs_cache(matrix, mem, i, j);
                max = Math.max(len, max);
            }
        }
        return max;
    }

    // the reason why we do not worry about previous visited because if I am exploring from x -> y,
    // then there is no way, I can reach x from longest increasing path starting y. because x < y
    public int dfs_cache(int[][] matrix, int[][] mem, int i, int j) {
        if(mem[i][j] != 0) {
            return mem[i][j];
        }
        for(int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            if(newI >= 0 && newJ >= 0 && newI < matrix.length && newJ < matrix[0].length
                    && matrix[i][j] < matrix[newI][newJ]) {
                int len = dfs_cache(matrix, mem, newI, newJ);
                mem[i][j] = Math.max(mem[i][j], len);
            }
        }
        return ++mem[i][j];
    }


    public int longestIncreasingPath_with_top_sort(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i)
            System.arraycopy(grid[i], 0, matrix[i + 1], 1, n);

        // calculate outdegrees
        int[][] outdegree = new int[m + 2][n + 2];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                for (int[] d: directions)
                    if (matrix[i][j] < matrix[i + d[0]][j + d[1]])
                        outdegree[i][j]++;

        // find leaves who have zero out degree as the initial level
        n += 2;
        m += 2;
        List<int[]> leaves = new ArrayList<>();
        for (int i = 1; i < m - 1; ++i)
            for (int j = 1; j < n - 1; ++j)
                if (outdegree[i][j] == 0) leaves.add(new int[]{i, j});

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            List<int[]> newLeaves = new ArrayList<>();
            for (int[] node : leaves) {
                for (int[] d:directions) {
                    int x = node[0] + d[0], y = node[1] + d[1];
                    if (matrix[node[0]][node[1]] > matrix[x][y])
                        if (--outdegree[x][y] == 0)
                            newLeaves.add(new int[]{x, y});
                }
            }
            leaves = newLeaves;
        }
        return height;
    }


    // time limit exceeds
    public int longestIncreasingPath_normal_dfs(int[][] matrix) {
        if(matrix.length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];;
        int max = 0;
        for(int i=0; i < matrix.length; i++) {
            for(int j=0; j < matrix[0].length; j++) {
                int len = dfs(matrix, visited, i, j);
                max = Math.max(len, max);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        int max = 1;
        for(int[] direction : directions) {
            int newI = i + direction[0];
            int newJ = i + direction[1];
            if(newI >= 0 && newJ >= 0 && newI < matrix.length && newJ < matrix[0].length
                    && !visited[newI][newJ] && matrix[i][j] < matrix[newI][newJ]) {
                int len = dfs(matrix, visited, newI, newJ);
                max = Math.max(max, 1 + len);
            }
        }
        visited[i][j] = false;
        return max;
    }

}
