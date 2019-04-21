package com.piyush.sahu.lectures.graph.standardAlgo;

import java.util.LinkedList;

class Edge{
    int from;
    int to;
    public Edge(int from, int to){
        this.from = from;
        this.to = to;
    }
}

class Graph{

    int V;
    LinkedList<Edge>[] list;
    int E;

    public Graph(int V){
        this.V = V;
        this.list = new LinkedList[V];
        for(int i=0; i < V; i++){
            list[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to){
        list[from].add(new Edge(from, to));
        E++;
    }

    public Edge[] edges(){
        int count = 0;
        Edge[] edges = new Edge[E];
        for(LinkedList<Edge> linkedList: list){
            for(Edge edge: linkedList){
                edges[count++] = edge;
            }
        }
        return edges;
    }
}

public class DetectCycleUsingUnionFind {
    public boolean isCycle(Graph graph){
        Edge[] edges = graph.edges();
        int[] parent = new int[graph.V];
        int[] rank = new int[graph.V];
        for(int i=0; i < graph.V; i++){
            parent[i] = i;
        }
        for(Edge edge: edges){
            if(union(parent, rank, edge.from, edge.to)){
                return true;
            }
        }
        return false;
    }

    private int find(int[] parent, int p){
        if(p == parent[p]){
            return p;
        }
        return find(parent, parent[p]);
    }

    private boolean union(int[] parent, int[] rank, int p, int q){
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

    public static void main(String[] args) {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        DetectCycleUsingUnionFind detectCycleUsingUnionFind = new DetectCycleUsingUnionFind();
        System.out.println(detectCycleUsingUnionFind.isCycle(graph));
    }
}
