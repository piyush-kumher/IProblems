package com.piyush.psds.aug_6_2020_streak.graph.traversal;


import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;
import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyMatrixGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void main(String[] args) {
        BFS bfs = new BFS();
        AdjacencyListGraph<Integer> l = new AdjacencyListGraph<>(9);
        l.addEdge(0, 1);
        l.addEdge(0, 2);
        l.addEdge(0, 8);
        l.addEdge(1, 3);
        l.addEdge(1, 4);
        l.addEdge(4, 5);
        l.addEdge(4, 6);
        l.addEdge(2, 7);
        bfs.bfsAdjacencyList(l, 0);

        System.out.println();

        AdjacencyMatrixGraph m = new AdjacencyMatrixGraph(9);
        m.addEdge(0, 1);
        m.addEdge(0, 2);
        m.addEdge(0, 8);
        m.addEdge(1, 3);
        m.addEdge(1, 4);
        m.addEdge(4, 5);
        m.addEdge(4, 6);
        m.addEdge(2, 7);
        bfs.bfsAdjacencyMatrix(m, 0);
    }

    public void bfsAdjacencyList(AdjacencyListGraph<Integer> l, Integer startNode) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[l.getNoOfVertices()];
        q.add(startNode);
        while (!q.isEmpty()) {
            final Integer e = q.remove();
            visited[e] = true;
            final LinkedList<Integer> list = l.getAdjacencyList()[e];
            list.stream().filter(i -> !visited[i]).forEach(q::add);
            System.out.print(e + " ");
        }
    }

    public void bfsAdjacencyMatrix(AdjacencyMatrixGraph m, Integer startNode) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[m.getNumberOfVertices()];
        q.add(startNode);
        while (!q.isEmpty()) {
            final Integer e = q.remove();
            visited[e] = true;
            final int[] breathEles = m.getAdjacencyMatrix()[e];
            for (int i = 0; i < m.getNumberOfVertices() ; i++) {
                if (!visited[i] && breathEles[i] == 1) {
                    q.add(i);
                }
            }
            System.out.print(e + " ");
        }
    }

}
