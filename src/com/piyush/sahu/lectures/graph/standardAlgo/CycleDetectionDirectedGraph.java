package com.piyush.sahu.lectures.graph.standardAlgo;

import com.piyush.sahu.lectures.graph.entities.AdjacencyListGraph;

/**
 * https://www.geeksforgeeks.org/strongly-connected-components/
 * Complexity: O(E+V)
 */
public class CycleDetectionDirectedGraph {


    public static boolean isCycleExists(AdjacencyListGraph<Integer> graph) {
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        boolean[] recStack = new boolean[graph.getNoOfVertices()];
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if(!visited[i]) {
                if (cycleDetectionUtil(graph, i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean cycleDetectionUtil(AdjacencyListGraph<Integer> graph, Integer node,
                                              boolean[] visited, boolean[] recStack) {
        if (recStack[node]) {
            return true;
        }
        if(visited[node]){
            return false;
        }
        visited[node] = true;
        recStack[node] = true;
        for (int nextNode : graph.getAdjacencyList()[node]) {
            if (cycleDetectionUtil(graph, nextNode, visited, recStack)) {
                return true;
            }
        }
        recStack[node] = false;
        return false;
    }


    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        AdjacencyListGraph<Integer> new_graph = new AdjacencyListGraph<>(6);
        new_graph.addEdge(0, 1);
        new_graph.addEdge(1, 2);
        new_graph.addEdge(2, 3);
        new_graph.addEdge(5, 4);
        new_graph.addEdge(4, 3);
        new_graph.addEdge(5, 1);
        System.out.println(isCycleExists(graph));
        System.out.println(isCycleExists(new_graph));
    }

}
