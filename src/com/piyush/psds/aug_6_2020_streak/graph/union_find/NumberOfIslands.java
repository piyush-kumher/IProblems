package com.piyush.psds.aug_6_2020_streak.graph.union_find;

import java.util.LinkedList;

public class NumberOfIslands {

    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '0', '0', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '1', '0', '0'},
//                {'0', '0', '0', '1', '1'}
//        };
        char[][] grid = {
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '1'},
                {'1', '1', '1', '0', '1'}
        };
        char[][] grid1 = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        NumberOfIslands noi = new NumberOfIslands();
        System.out.println(noi.numIslands_approach2_union(grid1));
    }

    public int numIslands_approach1_dfs(char[][] grid) {
        int count = 0;
        if (grid != null && grid.length > 0) {
            int rows = grid.length;
            int columns = grid[0].length;
            int v = rows * columns;
            Graph g = new Graph(v);
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i + 1 < grid.length && grid[i][j] == '1' && grid[i + 1][j] == '1') {
                        g.addEdge(i * columns + j, (i + 1) * columns + j);
                    }
                    if (j + 1 < columns && grid[i][j] == '1' && grid[i][j + 1] == '1') {
                        g.addEdge(i * columns + j, i * columns + j + 1);
                    }
                }
            }

            boolean[] visited = new boolean[v];
            for (int i = 0; i < v; i++) {
                if (!visited[i] && grid[i / grid[0].length][i % grid[0].length] != '0') {
                    dfs(g, visited, i);
                    count++;
                }
            }
        }

        return count;
    }


    public int numIslands_approach2_union(char[][] grid) {
        int count = 0;
        if (grid != null && grid.length > 0) {
            int rows = grid.length;
            int columns = grid[0].length;
            int[] rank = new int[rows * columns];
            int[] parent = new int[rows * columns];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (i + 1 < rows && grid[i][j] == '1' && grid[i + 1][j] == '1') {
                        union(i * columns + j, (i + 1) * columns + j, parent, rank);
                    }
                    if (j + 1 < columns && grid[i][j] == '1' && grid[i][j + 1] == '1') {
                        union(i * columns + j, i * columns + j + 1, parent, rank);
                    }
                }
            }
            int[] rootCount = new int[rows * columns];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (grid[i][j] == '1') {
                        int root = findParent(i * columns + j, parent);
                        if (rootCount[root] == 0) {
                            count++;
                        }
                        rootCount[root] = rootCount[root] + 1;
                    }
                }
            }
            for(int i=0; i < rows*columns; i++) {
                System.out.print(rootCount[i] + " ");
            }
        }
        return count;
    }

    private void union(int a, int b, int[] parent, int[] rank) {
        int parentA = findParent(a, parent);
        int parentB = findParent(b, parent);
        if (parentA == parentB) {
            return;
        }
        if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
        } else if (rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
            rank[parentA] = rank[parentA] + 1;
        }
    }

    private int findParent(int a, int[] parent) {
        if (a == parent[a]) {
            return a;
        }
        return findParent(parent[a], parent);
    }

    private void dfs(Graph g, boolean[] visited, int s) {
        final LinkedList<Integer> list = g.adjacencyList[s];
        visited[s] = true;
        for (Integer i : list) {
            if (!visited[i]) {
                dfs(g, visited, i);
            }
        }
    }

    class Graph {

        int v;
        LinkedList<Integer>[] adjacencyList;

        public Graph(int v) {
            this.v = v;
            adjacencyList = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int a, int b) {
            adjacencyList[a].add(b);
            adjacencyList[b].add(a);
        }

    }

}


