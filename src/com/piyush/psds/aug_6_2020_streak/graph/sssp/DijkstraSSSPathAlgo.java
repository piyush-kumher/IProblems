package com.piyush.psds.aug_6_2020_streak.graph.sssp;

import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdge;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdgeGraph;

import java.util.*;

/*
Example diagram taken from here:
https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
 */

class Node {
    int v;
    int dist;

    public Node(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}

public class DijkstraSSSPathAlgo {

    public static void main(String[] args) {
        WeightedEdgeGraph graph = new WeightedEdgeGraph(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(1, 0, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(7, 0, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(3, 2, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(8, 2, 2);
        graph.addEdge(3, 4, 9);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 2, 4);
        graph.addEdge(2, 5, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(3, 5, 14);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 8, 6);
        graph.addEdge(8, 6, 6);
        graph.addEdge(6, 7, 1);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 7, 7);
        graph.addEdge(7, 1, 11);
        graph.addEdge(1, 7, 11);
        DijkstraSSSPathAlgo sss = new DijkstraSSSPathAlgo();
        final int[] sssp = sss.sssp(graph, 0);
        for(int i : sssp) {
            System.out.print(" " + i);
        }
    }

    private int[] sssp(WeightedEdgeGraph graph, int s) {
        int[] dist = new int[graph.v];
        boolean[] settled = new boolean[graph.v];
        for (int i = 0; i < graph.v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        dist[s] = 0;
        q.add(new Node(s, dist[s]));
        while (!q.isEmpty()) {
            final Node ele = q.remove();
            settled[ele.v] = true;
            LinkedList<WeightedEdge> list = graph.adjacencyList[ele.v];
            for (WeightedEdge e : list) {
                if (!settled[e.to] && ele.dist + e.weight < dist[e.to]) {
                    dist[e.to] = ele.dist + e.weight;
                    q.add(new Node(e.to, dist[e.to]));
                }
            }
        }
        return dist;
    }

}
