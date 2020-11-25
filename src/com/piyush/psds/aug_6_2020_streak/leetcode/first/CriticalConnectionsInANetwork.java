package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

public class CriticalConnectionsInANetwork {

    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(new ArrayList<>(Arrays.asList(0, 1)));
        connections.add(new ArrayList<>(Arrays.asList(1, 2)));
        connections.add(new ArrayList<>(Arrays.asList(2, 0)));
        connections.add(new ArrayList<>(Arrays.asList(1, 3)));
        CriticalConnectionsInANetwork t = new CriticalConnectionsInANetwork();
        System.out.println(t.criticalConnections(4, connections));
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph g = new Graph(n);
        List<List<Integer>> res = new ArrayList<>();
        connections.forEach(c -> g.addEdge(c.get(0), c.get(1)));
        for (int i = 0; i < g.size; i++) {
            if (g.discoverTime[i] == -1) {
                dfs(g, i, res, 0);
            }
        }
        return res;
    }

    private void dfs(Graph g, int i, List<List<Integer>> res, int count) {
        if (g.discoverTime[i] != -1) {
            return;
        }
        g.lowestVertexInSubTree[i] = g.discoverTime[i] = count++;
        for (int ele : g.adjacencyList[i]) {
            if (g.discoverTime[ele] == -1) {
                g.parent[ele] = i;
                dfs(g, ele, res, count);

                g.lowestVertexInSubTree[i] = Math.min(g.lowestVertexInSubTree[ele], g.lowestVertexInSubTree[i]);
                if (g.lowestVertexInSubTree[ele] > g.discoverTime[i]) {
                    res.add(Arrays.asList(ele, i));
                }
            } else if (g.parent[i] != ele) {
                g.lowestVertexInSubTree[i] = Math.min(g.lowestVertexInSubTree[ele], g.lowestVertexInSubTree[i]);
            }
        }
    }

    // time limit exceeds
    public List<List<Integer>> criticalConnections_1(int n, List<List<Integer>> connections) {
        Graph1 g = new Graph1(n);
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> l : connections) {
            g.add(l.get(0), l.get(1));
        }
        boolean[] visited;
        for (int i = 0; i < connections.size(); i++) {
            if (i > 0) {
                g.add(connections.get(i - 1).get(0), connections.get(i - 1).get(1));
            }
            g.removeEdge(connections.get(i).get(0), connections.get(i).get(1));
            visited = new boolean[n];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    count++;
                    if (count > 1) {
                        break;
                    }
                    dfs(g, j, visited);
                }
            }
            if (count > 1) {
                result.add(new ArrayList<>(Arrays.asList(connections.get(i).get(0), connections.get(i).get(1))));
            }
        }
        return result;
    }

    private void dfs(Graph1 g, int i, boolean[] visited) {
        visited[i] = true;
        for (Integer next : g.adjList[i]) {
            if (!visited[next]) {
                dfs(g, next, visited);
            }
        }
    }

    class Graph {
        int size;
        LinkedList<Integer>[] adjacencyList;
        int[] discoverTime;
        int[] parent;
        int[] lowestVertexInSubTree;

        public Graph(int size) {
            this.size = size;
            adjacencyList = new LinkedList[size];
            discoverTime = new int[size];
            parent = new int[size];
            lowestVertexInSubTree = new int[size];
            Arrays.fill(discoverTime, -1);
            Arrays.fill(parent, -1);
            Arrays.fill(lowestVertexInSubTree, -1);
            for (int i = 0; i < size; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int i, int j) {
            adjacencyList[i].add(j);
            adjacencyList[j].add(i);
        }
    }

    class Graph1 {
        List<Integer>[] adjList;

        public Graph1(int size) {
            adjList = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                adjList[i] = new ArrayList<>();
            }
        }

        public void add(int i, int j) {
            adjList[i].add(j);
            adjList[j].add(i);
        }

        public void removeEdge(int i, int j) {
            adjList[i].remove(new Integer(j));
            adjList[j].remove(new Integer(i));
        }
    }

}
