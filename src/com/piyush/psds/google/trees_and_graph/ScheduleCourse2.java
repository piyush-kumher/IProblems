package com.piyush.psds.google.trees_and_graph;

import java.util.LinkedList;
import java.util.Stack;

public class ScheduleCourse2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        Stack<Integer> stk = new Stack<>();
        for(int[] pre : prerequisites) {
            g.addEdge(pre[1], pre[0]);
        }
        int[] visited = new int[numCourses];
        for(int i=0; i < numCourses; i++) {
            if(visited[i] == 0) {
                if(!dfs(g, stk, visited, i)) {
                    return new int[0];
                }
            }
        }
        int[] result = new int[numCourses];
        int count = 0;
        while(!stk.isEmpty()) {
            result[count++] = stk.pop();
        }
        return result;
    }

    private boolean dfs(Graph g, Stack<Integer> stk, int[] visited, int node) {
        visited[node] = 1;
        for(int nextNode : g.adj[node]) {
            if(visited[nextNode] == 0) {
                if(!dfs(g, stk, visited, nextNode)) {
                    return false;
                }
            } else if(visited[nextNode] == 1) {
                return false;
            }
        }
        visited[node] = 2;
        stk.push(node);
        return true;
    }

    class Graph {
        int v;
        LinkedList<Integer>[] adj;
        public Graph(int v) {
            this.v = v;
            this.adj = new LinkedList[v];
            for(int i=0; i < v; i++) {
                this.adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int a, int b) {
            adj[a].add(b);
        }
    }

}
