package com.piyush.psds.aug_6_2020_streak.graph.mst;

import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdge;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdgeGraph;

import java.util.*;

class Node {
    int v;
    int dist;

    public Node(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}

class Result {
    int[] dist;
    int[] edgeTo;

    public Result(int[] edgeTo, int[] dist) {
        this.dist = dist;
        this.edgeTo = edgeTo;
    }
}

public class EagerPrimsMST {

    public static void main(String[] args) {
        WeightedEdgeGraph graph = new WeightedEdgeGraph(7);
        graph.addEdge(0, 1, 28);
        graph.addEdge(1, 0, 28);
        graph.addEdge(0, 5, 10);
        graph.addEdge(5, 0, 10);
        graph.addEdge(5, 4, 25);
        graph.addEdge(4, 5, 25);
        graph.addEdge(4, 3, 22);
        graph.addEdge(3, 4, 22);
        graph.addEdge(3, 2, 12);
        graph.addEdge(2, 3, 12);
        graph.addEdge(2, 1, 16);
        graph.addEdge(1, 2, 16);
        graph.addEdge(1, 6, 14);
        graph.addEdge(6, 1, 14);
        graph.addEdge(6, 4, 24);
        graph.addEdge(4, 6, 24);
        graph.addEdge(3, 6, 18);
        graph.addEdge(6, 3, 18);
        EagerPrimsMST mst = new EagerPrimsMST();
        final Result r = mst.mst(graph, 0);
        int total = 0;
        for(int i = 0; i < 7; i++) {
            System.out.println("from:" + i + " ,to=" + r.edgeTo[i] + " ,dist=" + r.dist[i]);
            total += r.dist[i];
        }
        System.out.println("TOTAL: " + total);
    }

    public Result mst(WeightedEdgeGraph graph, int s) {
        int[] dist = new int[graph.v];
        int[] edgeTo = new int[graph.v];
        boolean[] visited = new boolean[graph.v];
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(node -> node.dist));
        for (int i = 0; i < graph.v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        edgeTo[s] = -1;
        q.add(new Node(s, dist[s]));
        while (!q.isEmpty()) {
            final Node min = q.remove();
            if (visited[min.v]) {
                continue;
            }
            visited[min.v] = true;
            LinkedList<WeightedEdge> edges = graph.adjacencyList[min.v];
            for (WeightedEdge edge : edges) {
                if (!visited[edge.to] && dist[edge.to] > edge.weight) {
                    dist[edge.to] = edge.weight;
                    edgeTo[edge.to] = edge.from;
                    q.add(new Node(edge.to, edge.weight));
                }
            }
        }
        return new Result(edgeTo, dist);
    }

}
