package com.piyush.psds.aug_6_2020_streak.graph.connected_component;

import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;

import java.util.LinkedList;
import java.util.Stack;

public class KosarajuAlgo {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> al = new AdjacencyListGraph<>(8);
        al.addEdge(0, 1);
        al.addEdge(1, 2);
        al.addEdge(2, 0);
        al.addEdge(2, 4);
        al.addEdge(4, 3);
        al.addEdge(3, 5);
        al.addEdge(5, 3);
        al.addEdge(6, 3);
        al.addEdge(7, 6);

        KosarajuAlgo algo = new KosarajuAlgo();
        algo.connectedComponent(al);
    }

    public void connectedComponent(final AdjacencyListGraph<Integer> graph) {
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (!visited[i]) {
                dfsMod(graph, visited, s, i);
            }
        }
        final AdjacencyListGraph<Integer> reverse = reverse(graph);
        visited = new boolean[reverse.getNoOfVertices()];
        while (!s.isEmpty()) {
            int el = s.pop();
            if (!visited[el]) {
                dfs(reverse, visited, s.pop());
                System.out.println();
            }
        }
    }

    private void dfs(AdjacencyListGraph<Integer> graph, boolean[] visited, int i) {
        visited[i] = true;
        System.out.print(i + " ");
        final LinkedList<Integer> list = graph.getAdjacencyList()[i];
        for (Integer item : list) {
            if (!visited[item]) {
                dfs(graph, visited, item);
            }
        }
    }

    private void dfsMod(AdjacencyListGraph<Integer> graph, boolean[] visited, Stack<Integer> s, int i) {
        visited[i] = true;
        final LinkedList<Integer> list = graph.getAdjacencyList()[i];
        for (Integer item : list) {
            if (!visited[item]) {
                dfsMod(graph, visited, s, item);
            }
        }
        s.push(i);
    }

    private AdjacencyListGraph<Integer> reverse(AdjacencyListGraph<Integer> graph) {
        AdjacencyListGraph<Integer> reverse = new AdjacencyListGraph<>(graph.getNoOfVertices());
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            for (Integer dest : graph.getAdjacencyList()[i]) {
                reverse.addEdge(dest, i);
            }
        }
        return reverse;
    }


}
