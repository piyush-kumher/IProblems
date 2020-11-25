package com.piyush.psds.lectures.graph.standardAlgo;

import com.piyush.psds.lectures.graph.entities.AdjacencyMatrixGraph;

public class AdjancencyMatrixRepresentation {

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
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
