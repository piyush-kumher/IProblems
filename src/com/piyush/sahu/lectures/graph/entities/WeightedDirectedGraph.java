package com.piyush.sahu.lectures.graph.entities;

import java.util.LinkedList;

public class WeightedDirectedGraph {

    private int V;
    private int E;
    private LinkedList<DirectedEdge>[] adjacencyLists;

    public WeightedDirectedGraph(int V){
        this.V = V;
        this.E = 0;
        this.adjacencyLists = new LinkedList[V];
        for(int i=0; i < V; i++){
            adjacencyLists[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v, int weight){
        adjacencyLists[u].add(new DirectedEdge(u, v, weight));
        E++;
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public LinkedList<DirectedEdge> edges(){
        LinkedList<DirectedEdge> list = new LinkedList<>();
        for(int i=0; i < V; i++){
            list.addAll(adjacencyLists[i]);
        }
        return list;
    }

    public LinkedList<DirectedEdge>[] getAdjacencyLists() {
        return adjacencyLists;
    }

    public LinkedList<DirectedEdge> getAdjacencyList(int node) {
        return adjacencyLists[node];
    }
}
