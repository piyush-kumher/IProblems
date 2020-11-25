package com.piyush.psds.aug_6_2020_streak.graph.cycle_detection;

import com.piyush.psds.aug_6_2020_streak.graph.union_find.UnionFind;
import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;

import java.util.LinkedList;

public class CycleDetectionUndirectedGraphUnionFind {

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        CycleDetectionUndirectedGraphUnionFind cd = new CycleDetectionUndirectedGraphUnionFind();
        boolean cycle = cd.detect(graph);
        System.out.println(cycle);
        System.out.println(cd.isCyclic(graph));

        graph = new AdjacencyListGraph<>(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        cycle = cd.detect(graph);
        System.out.println(cycle);
        System.out.println(cd.isCyclic(graph));
    }

    private boolean detect(AdjacencyListGraph<Integer> graph) {
        UnionFind uf = new UnionFind(graph.getNoOfVertices());
        final LinkedList<Integer>[] adjacencyList = graph.getAdjacencyList();
        for (int from = 0; from < adjacencyList.length; from++) {
            for (Integer to : adjacencyList[from]) {
                if (uf.union(from, to)) {
                    return true;
                }
            }
        }
        return false;
    }

    Boolean isCyclic(AdjacencyListGraph<Integer> graph) {
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        for (int u = 0; u < graph.getNoOfVertices(); u++) {
            if (!visited[u] && dfsCycle(graph, u, visited, -1)) {
                return true;
            }
        }

        return false;
    }


    private boolean dfsCycle(AdjacencyListGraph<Integer> graph, int v, boolean[] visited, int parent) {
        visited[v] = true;
        LinkedList<Integer> edges = graph.getAdjacencyList()[v];
        for (Integer vtx : edges) {
            if (!visited[vtx]) {
                if (dfsCycle(graph, vtx, visited, v)) {
                    return true;
                }
            } else if (parent != vtx) {
                return true;
            }
        }
        return false;
    }

}
