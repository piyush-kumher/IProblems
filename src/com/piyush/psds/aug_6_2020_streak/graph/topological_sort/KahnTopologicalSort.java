package com.piyush.psds.aug_6_2020_streak.graph.topological_sort;

import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;

import java.util.LinkedList;
import java.util.Queue;

public class KahnTopologicalSort {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(6);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        KahnTopologicalSort t = new KahnTopologicalSort();
        t.topologicalSort(graph);
    }

    public void topologicalSort(AdjacencyListGraph<Integer> graph) {
        int[] inDegree = new int[graph.getNoOfVertices()];
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            for (int to : graph.getAdjacencyList()[i]) {
                inDegree[to] = inDegree[to] + 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer i = queue.poll();
            System.out.print(i + " ");
            for (int adjNode : graph.getAdjacencyList()[i]) {
                inDegree[adjNode]--;
                if (inDegree[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }
    }

}
