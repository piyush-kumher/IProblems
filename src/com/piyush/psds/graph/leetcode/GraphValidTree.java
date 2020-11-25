package com.piyush.psds.graph.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/graph-valid-tree/
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        List<Set<Integer>> adjList = new ArrayList<>();
        for(int i=0; i < n; i++){
            adjList.set(i, new HashSet<>());
        }
        int[] indegree = new int[n];
        for(int i=0; i < edges.length; i++){
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        int count = 0;
        Queue<Integer> queue  = new LinkedList<>();
        for(int i=0; i < n; i++){
            if(indegree[i] == 1){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int node = queue.remove();
            count ++;
            for(int nextNode : adjList.get(node)){
                if(--indegree[nextNode] == 1){
                    queue.add(nextNode);
                }
            }
        }
        return count == n;
    }

    private int find(int[] parent, int p){
        if(p == parent[p]){
            return p;
        }
        return find(parent, parent[p]);
    }

    private boolean unionCycle(int[] parent, int[] rank, int p, int q){
        int rootP = find(parent, p);
        int rootQ = find(parent, q);
        if(rootP == rootQ){
            return true;
        }
        if(rank[rootP] < rank[rootQ]){
            parent[rootP] = rootQ;
        }else if(rank[rootQ] < rank[rootP]){
            parent[rootQ] = rootP;
        }else{
            parent[rootQ] = rootP;
            rank[rootQ]++;
        }
        return false;
    }

    public boolean validTreeUnionFind(int n, int[][] edges) {
        int[] rank = new int[n];
        int[] parent = new int[n];
        for(int i=0; i < n; i++){
            parent[i] = i;
        }
        for(int i=0; i < edges.length; i++){
            if(unionCycle(parent, rank, edges[i][0], edges[0][i])){
                return true;
            }
        }
        return false;
    }

}
