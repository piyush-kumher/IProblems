package com.piyush.psds.lectures.graph.standardAlgo;

import com.piyush.psds.lectures.graph.entities.AdjacencyListGraph;

public class AdjacencyListRepresentation {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 0);
        graph.addEdge(1, 4);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        System.out.println(graph);
    }

}
