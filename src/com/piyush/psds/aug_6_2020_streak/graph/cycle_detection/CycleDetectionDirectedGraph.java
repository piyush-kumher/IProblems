package com.piyush.psds.aug_6_2020_streak.graph.cycle_detection;

import com.piyush.psds.lectures.graph.entities.AdjacencyListGraph;

import java.util.Arrays;
import java.util.List;

public class CycleDetectionDirectedGraph {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        System.out.println(isCycleExists(graph));

        graph = new AdjacencyListGraph<>(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(5, 4);
        graph.addEdge(4, 3);
        graph.addEdge(5, 1);
        System.out.println(isCycleExists(graph));
    }

    private static boolean isCycleExists(AdjacencyListGraph<Integer> graph) {
        int[] visited = new int[graph.getNoOfVertices()];
        Arrays.fill(visited, -1);
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (visited[i] == -1 && dfsCycle(graph, visited, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean dfsCycle(AdjacencyListGraph<Integer> graph, int[] visited, int i) {
        visited[i] = 0;
        List<Integer> list = graph.getAdjacencyList()[i];
        for (Integer vtx : list) {
            if (visited[vtx] == 0) {
                return true;
            }
            if (visited[vtx] == -1 && dfsCycle(graph, visited, vtx)) {
                return true;
            }
        }
        visited[i] = 1;
        return false;
    }

}
