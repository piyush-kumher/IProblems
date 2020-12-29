package com.piyush.psds.facebook.trees_and_graph;

import java.util.LinkedList;
import java.util.Queue;

public class BipirateGraph {

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for(int i = 0; i < graph.length; i++) {
            colors[i] = -1;
        }
        for(int i=0; i < graph.length; i++) {
            if(colors[i] == -1) {
                if(!isBipartite(graph, i, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBipartite(int[][] graph, int node, int[] colors) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        colors[node] = 1;
        while(!q.isEmpty()) {
            int u = q.remove();
            for(int v : graph[u]) {
                if(colors[u] == colors[v]) {
                    return false;
                }
                if(colors[v] == -1) {
                    colors[v] = 1 - colors[u];
                    q.add(v);
                }
            }
        }
        return true;
    }

}
