package com.piyush.psds.google.trees_and_graph;

import java.util.*;

public class NumberOfIslands {

    // union find
    public int numIslands(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        Unionfind u = new Unionfind(n*m);
        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                if(j < m-1 && grid[i][j] == '1' && grid[i][j+1] == '1') {
                    u.union(i*m+j, i*m+(j+1));
                }
                if(i < n-1 && grid[i][j] == '1' && grid[i+1][j] == '1') {
                    u.union(i*m+j, (i+1)*m+j);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                if(grid[i][j] == '1') {
                    int parent = u.findParent(i*m+j);
                    if(!map.containsKey(parent)) {
                        map.put(parent, 1);
                    }
                }
            }
        }
        return map.size();
    }

    class Unionfind {
        public int[] parent;
        public int[] rank;

        public Unionfind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i=0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int parentA = findParent(a);
            int parentB = findParent(b);
            if(parentA == parentB) {
                return;
            }
            if(rank[parentA] > rank[parentB]) {
                parent[parentB] = parentA;
            } else if(rank[parentB] > rank[parentA]) {
                parent[parentA] = parentB;
            } else {
                parent[parentB] = parentA;
                rank[parentA] += 1;
            }
        }

        public int findParent(int a) {
            if(parent[a] == a) {
                return a;
            }
            return findParent(parent[a]);
        }
    }




    //graph
    public int numIslands_graph(char[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        Graph g = new Graph(n*m);
        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                if(j < m-1 && grid[i][j] == '1' && grid[i][j+1] == '1') {
                    g.addBEdge(i*m+j, i*m+(j+1));
                }
                if(i < n-1 && grid[i][j] == '1' && grid[i+1][j] == '1') {
                    g.addBEdge(i*m+j, (i+1)*m+j);
                }
            }
        }
        boolean[] visited = new boolean[n*m];
        int count = 0;
        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                if(grid[i][j] == '1' && !visited[i*m+j]) {
                    dfs(g, visited, i*m+j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(Graph g, boolean[] visited, int node) {
        visited[node] = true;
        for(int nextNode : g.adj[node]) {
            if(!visited[nextNode]) {
                dfs(g, visited, nextNode);
            }
        }
    }

    class Graph {
        int n;
        LinkedList<Integer>[] adj;
        public Graph(int v) {
            this.n = v;
            adj = new LinkedList[v];
            for(int i=0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addBEdge(int a, int b) {
            adj[b].add(a);
            adj[a].add(b);
        }
    }
}
