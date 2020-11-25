package com.piyush.psds.aug_6_2020_streak.graph.sssp;

import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdge;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdgeGraph;

import java.util.List;

public class BellmanFordAlgo {

    public static void main(String[] args) {
        WeightedEdgeGraph graph = new WeightedEdgeGraph(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(1, 0, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(7, 0, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 2, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(8, 2, 2);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 2, 4);
        graph.addEdge(2, 5, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(8, 6, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 7, 7);
        graph.addEdge(7, 1, 11);
        graph.addEdge(1, 7, 11);
        BellmanFordAlgo sss = new BellmanFordAlgo();
        int[] sssp = sss.sssp(graph, 0);
        for (int i : sssp) {
            System.out.print(" " + i);
        }
        System.out.println();

        //Experiment for negative cycle
        graph = new WeightedEdgeGraph(4);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 5);
        graph.addEdge(3, 2, 3);
        graph.addEdge(2, 1, -10);
        sssp = sss.sssp(graph, 0);
        for (int i : sssp) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    private int[] sssp(WeightedEdgeGraph graph, int s) {
        int[] dist = new int[graph.v];
        for (int i = 0; i < graph.v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        for (int i = 0; i < graph.v - 1; i++) {
            final List<WeightedEdge> edges = graph.getEdges();
            for (WeightedEdge edge : edges) {
                if (dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                }
            }
        }

        //checking for negative cycle
        final List<WeightedEdge> edges = graph.getEdges();
        for (WeightedEdge edge : edges) {
            if (dist[edge.to] != Integer.MAX_VALUE && dist[edge.from] + edge.weight < dist[edge.to]) {
                System.out.println("there is a negative cycle");
            }
        }

        return dist;
    }

}
