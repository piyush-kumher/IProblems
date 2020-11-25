package com.piyush.psds.aug_6_2020_streak.graph.traversal;

import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyListGraph;
import com.piyush.psds.aug_6_2020_streak.graph.entities.AdjacencyMatrixGraph;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {

    public static void main(String[] args) {
        DFS bfs = new DFS();
        AdjacencyListGraph<Integer> l = new AdjacencyListGraph<>(9);
        l.addEdge(0, 1);
        l.addEdge(0, 2);
        l.addEdge(0, 8);
        l.addEdge(1, 3);
        l.addEdge(1, 4);
        l.addEdge(4, 5);
        l.addEdge(4, 6);
        l.addEdge(2, 7);
        bfs.dfsAdjacencyList(l, 0);

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
        bfs.dfsAdjacencyMatrix(m, 0);
    }

    private void dfsAdjacencyMatrix(AdjacencyMatrixGraph m, int startNode) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited =  new boolean[m.getNumberOfVertices()];
        visited[startNode] = true;
        s.push(startNode);
        while (!s.isEmpty()) {
            final Integer e = s.pop();
            System.out.print(e + " ");
            final int[] adjEles = m.getAdjacencyMatrix()[e];
            for(int i=0; i < m.getNumberOfVertices(); i++) {
                if(!visited[i] && adjEles[i] == 1) {
                    s.push(i);
                }
            }
        }
    }

    private void dfsAdjacencyList(AdjacencyListGraph<Integer> l, int startNode) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited =  new boolean[l.getNoOfVertices()];
        visited[startNode] = true;
        s.push(startNode);
        while (!s.isEmpty()) {
            final Integer e = s.pop();
            System.out.print(e + " ");
            final LinkedList<Integer> adjEles = l.getAdjacencyList()[e];
            adjEles.stream().filter(i -> !visited[i]).forEach(s::push);
        }
    }

}
