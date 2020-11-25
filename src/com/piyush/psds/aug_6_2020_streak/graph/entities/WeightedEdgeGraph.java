package com.piyush.psds.aug_6_2020_streak.graph.entities;

import java.util.LinkedList;
import java.util.List;

public class WeightedEdgeGraph {

    public final LinkedList<WeightedEdge>[] adjacencyList;
    public final int v;
    public int e;

    public WeightedEdgeGraph(int v) {
        this.v = v;
        this.adjacencyList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacencyList[from].add(new WeightedEdge(from, to, weight));
        e++;
    }

    public List<WeightedEdge> getEdges() {
        LinkedList<WeightedEdge> l = new LinkedList<>();
        for (LinkedList<WeightedEdge> edges : adjacencyList) {
            l.addAll(edges);
        }
        return l;
    }
}