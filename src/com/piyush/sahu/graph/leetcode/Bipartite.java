package com.piyush.sahu.graph.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Bipartite {
    public boolean isBipartite(int[][] graph) {
        int[] colorMap = new int[graph.length];
        for(int i=0; i < graph.length; i++){
            colorMap[i] = -1;
        }
        for(int i=0; i < graph.length; i++){
            if(colorMap[i] == -1){
                if(!isBipartite(graph, i, colorMap)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isBipartite(int[][] graph, int node, int[] colorMap){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        colorMap[node] = 1;
        while(!queue.isEmpty()){
            int u = queue.remove();
            for(int v : graph[u]){
                if(colorMap[v] == colorMap[u]){
                    return false;
                }else if(colorMap[v] == -1){
                    colorMap[v] = 1-colorMap[u];
                    queue.add(v);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Bipartite b = new Bipartite();
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(b.isBipartite(graph));
    }
}
