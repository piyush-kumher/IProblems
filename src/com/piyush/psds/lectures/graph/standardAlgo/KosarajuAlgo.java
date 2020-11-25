package com.piyush.psds.lectures.graph.standardAlgo;

import java.util.*;

class AdjacencyListGraph {
    int numberOfNodes;
    LinkedList<Integer>[] adjacencyList;

    AdjacencyListGraph(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        adjacencyList = new LinkedList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    void addEdge(int from, int to) {
        adjacencyList[from].add(to);
    }
}

public class KosarajuAlgo {

    private static void dfsUtil(AdjacencyListGraph graph, int node,
                                Stack<Integer> stack, boolean[] visited) {
        visited[node] = true;
        for (int nextNode : graph.adjacencyList[node]) {
            if (!visited[nextNode]) {
                dfsUtil(graph, nextNode, stack, visited);
            }
        }
        stack.push(node);
    }

    private static void dfsUtil(AdjacencyListGraph graph, int node,
                                boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int nextNode : graph.adjacencyList[node]) {
            if (!visited[nextNode]) {
                dfsUtil(graph, nextNode, visited);
            }
        }
    }

    private static Stack<Integer> topologicalSort(AdjacencyListGraph graph) {
        boolean[] visited = new boolean[graph.numberOfNodes];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < graph.numberOfNodes; i++) {
            if (!visited[i]) {
                dfsUtil(graph, i, stack, visited);
            }
        }
        return stack;
    }

    private static AdjacencyListGraph reverse(AdjacencyListGraph graph) {
        AdjacencyListGraph reversedGraph = new AdjacencyListGraph(graph.numberOfNodes);
        for (int i = 0; i < graph.numberOfNodes; i++) {
            for (int node : graph.adjacencyList[i]) {
                reversedGraph.addEdge(node, i);
            }
        }
        return reversedGraph;
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(8);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(7, 5);
        //topological sort
        Stack<Integer> stack = topologicalSort(graph);
        //reverse the graph
        AdjacencyListGraph reversedGraph = reverse(graph);
        // dfs on reversed graph
        boolean[] visited = new boolean[graph.numberOfNodes];
        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            if (!visited[node]) {
                dfsUtil(reversedGraph, node, visited);
                System.out.println();
            }
        }
    }

}
