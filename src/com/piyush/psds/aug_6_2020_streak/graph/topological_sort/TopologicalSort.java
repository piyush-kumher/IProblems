package com.piyush.psds.aug_6_2020_streak.graph.topological_sort;

import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;

import java.util.Stack;

public class TopologicalSort {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(6);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        TopologicalSort t = new TopologicalSort();
        t.topologicalSort(graph);
    }

    public void topologicalSort(AdjacencyListGraph<Integer> graph) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (!visited[i]) {
                topologicalSortUtil(graph, i, visited, s);
            }
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
        System.out.println();
    }

    private void topologicalSortUtil(AdjacencyListGraph<Integer> graph, int n, boolean[] visited, Stack<Integer> s) {
        visited[n] = true;
        for (Integer ele : graph.getAdjacencyList()[n]) {
            if (!visited[ele]) {
                topologicalSortUtil(graph, ele, visited, s);
            }
        }
        s.push(n);
    }

}
