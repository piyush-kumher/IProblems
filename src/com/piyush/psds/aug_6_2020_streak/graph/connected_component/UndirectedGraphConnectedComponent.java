package com.piyush.psds.aug_6_2020_streak.graph.connected_component;

import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;

import java.util.LinkedList;

public class UndirectedGraphConnectedComponent {

    private final AdjacencyListGraph<Integer> graph;
    private final boolean[] visited;

    public UndirectedGraphConnectedComponent(AdjacencyListGraph<Integer> graph) {
        this.graph = graph;
        this.visited = new boolean[graph.getNoOfVertices()];
    }

    public int getNumberOfConnectedComponents() {
        int count = 0;
        for (int i = 0; i < graph.getNoOfVertices(); i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }
        System.out.println();
        return count;
    }

    private void dfs(int startNode) {
        visited[startNode] = true;
        System.out.print(startNode);
        final LinkedList<Integer> list = graph.getAdjacencyList()[startNode];
        for (Integer i : list) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph<Integer> l = new AdjacencyListGraph<>(15);
        l.addEdge(0, 1);
        l.addEdge(0, 2);
        l.addEdge(0, 8);
        l.addEdge(1, 3);
        l.addEdge(1, 4);
        l.addEdge(4, 5);
        l.addEdge(4, 6);
        l.addEdge(2, 7);
        l.addEdge(9, 10);
        l.addEdge(9, 11);
        l.addEdge(11, 12);
        l.addEdge(11, 13);
        l.addEdge(12, 14);

        UndirectedGraphConnectedComponent connectedComponent = new UndirectedGraphConnectedComponent(l);
        System.out.println(connectedComponent.getNumberOfConnectedComponents());
    }

}
