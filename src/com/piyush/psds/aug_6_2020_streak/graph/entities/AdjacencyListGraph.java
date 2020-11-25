package com.piyush.psds.aug_6_2020_streak.graph.entities;

import java.util.LinkedList;

public class AdjacencyListGraph<T> {

    private final int len;
    private final LinkedList<T>[] adjacencyList;


    public AdjacencyListGraph(int len) {
        this.len = len;
        this.adjacencyList = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public int getNoOfVertices() {
        return len;
    }

    public LinkedList<T>[] getAdjacencyList() {
        return adjacencyList;
    }

    public void addEdge(int i, T node) {
        adjacencyList[i].add(node);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            s.append(i).append(" is connected to:");
            for (T t : adjacencyList[i]) {
                s.append(" ").append(t).append(",");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
