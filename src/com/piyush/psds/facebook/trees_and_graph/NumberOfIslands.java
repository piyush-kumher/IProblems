package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        UF uf = new UF(r*c);
        for(int i=0; i < r; i++) {
            for(int j=0; j < c; j++) {
                if(i < r-1 && grid[i][j] == '1' && grid[i+1][j] == '1') {
                    uf.union(i*c + j, (i+1)*c+j);
                }
                if(j < c-1 && grid[i][j] == '1' && grid[i][j+1] == '1') {
                    uf.union(i*c+j, i*c+j+1);
                }
            }
        }
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i < r; i++) {
            for(int j=0; j < c; j++) {
                if(grid[i][j] == '1') {
                    int parent = uf.findParent(i*c+j);
                    set.add(parent);
                }
            }
        }
        return set.size();
    }

    class UF {
        int[] parents;
        int[] rank;

        public UF(int n) {
            parents = new int[n];
            rank = new int[n];
            for(int i=0; i < n; i++) {
                parents[i] = i;
            }
        }

        public void union(int a, int b) {
            int parentA = findParent(a);
            int parentB = findParent(b);
            if(parentA == parentB) {
                return;
            }
            if(rank[parentA] > rank[parentB]) {
                parents[parentB] = parentA;
            } else if(rank[parentA] < rank[parentB]) {
                parents[parentA] = parentB;
            } else {
                parents[parentB] = parentA;
                rank[parentA]++;
            }
        }

        public int findParent(int a) {
            if(a == parents[a]) {
                return a;
            }
            return findParent(parents[a]);
        }
    }

    public int numIslands_1(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int r = grid.length;
        int c = grid[0].length;
        Graph g = new Graph();
        for(int i=0; i < r; i++) {
            for(int j=0; j < c; j++) {
                if(i < r-1 && grid[i][j] == '1' && grid[i+1][j] == '1') {
                    g.addEdge(i*c + j, (i+1)*c+j);
                    g.addEdge((i+1)*c+j, i*c + j);
                }
                if(j < c-1 && grid[i][j] == '1' && grid[i][j+1] == '1') {
                    g.addEdge(i*c+j, i*c+j+1);
                    g.addEdge(i*c+j+1, i*c+j);
                }
            }
        }
        int count = 0;
        boolean[] visited = new boolean[r*c];
        for(int i=0; i < r; i++) {
            for(int j=0; j < c; j++) {
                if(!visited[i*c+j] && grid[i][j] == '1') {
                    System.out.println("i=" + i + ", j=" + j);
                    count++;
                    dfs(g, i*c+j, visited);
                }
            }
        }
        return count;
    }

    private void dfs(Graph g, int i, boolean[] visited) {
        visited[i] = true;
        if(g.adjList.containsKey(i)) {
            for(int next : g.adjList.get(i)) {
                if(!visited[next]) {
                    dfs(g, next, visited);
                }
            }
        }
    }

    class Graph {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        public void addEdge(int i, int j) {
            adjList.putIfAbsent(i, new ArrayList<>());
            adjList.get(i).add(j);
        }
    }

}
