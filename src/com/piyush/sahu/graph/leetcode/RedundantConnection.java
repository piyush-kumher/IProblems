package com.piyush.sahu.graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/redundant-connection/
 */
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i < edges.length; i++){
            if(checkForCycle(map, edges[i][0], edges[i][1])){
                return edges[i];
            }
        }
        return new int[0];
    }

    private boolean checkForCycle(Map<Integer, Integer> map, int p, int q){
        int rootP = find(map, p);
        int rootQ = find(map, q);
        if(rootP == rootQ){
            return true;
        }
        map.put(rootP, rootQ);
        return false;
    }

    private int find(Map<Integer, Integer> map, int node){
        if(map.containsKey(node)){
            return find(map, map.get(node));
        }
        return node;
    }


    public int[] findRedundantConnection2ndApproach(int[][] edges) {
        Set<Integer> seen = new HashSet<>();
        int MAX_EDGES = 1000;
        ArrayList<Integer>[] graph = new ArrayList[MAX_EDGES + 1];
        for(int i=0; i < MAX_EDGES; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge : edges){
            seen.clear();;
            if(!graph[edge[0]].isEmpty() && !graph[edge[1]].isEmpty() && dfs(edge[0], edge[1], seen, graph)){
                return edge;
            }
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        return new int[1];
    }

    private boolean dfs(int source, int target, Set<Integer> seen, ArrayList<Integer>[] graph) {
        if (!seen.contains(source)) {
            seen.add(source);
            if (source == target) return true;
            for (int nei: graph[source]) {
                if (dfs(nei, target, seen, graph)) return true;
            }
        }
        return false;
    }


}
