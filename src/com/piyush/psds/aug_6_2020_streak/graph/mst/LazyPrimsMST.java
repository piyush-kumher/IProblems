package com.piyush.psds.aug_6_2020_streak.graph.mst;

import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdge;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdgeGraph;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=jsmMtJpPnhU
 * https://www.coursera.org/lecture/algorithms-part2/prims-algorithm-HoHKu
 * <p>
 * LAZY & EAGER: two approach, EAGER implementation here..
 */

public class LazyPrimsMST {

    final Queue<WeightedEdge> resultedWeightedEdge = new LinkedList<>();
    PriorityQueue<WeightedEdge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
    boolean[] visited;

    public LazyPrimsMST(WeightedEdgeGraph graph, int s) {
        visited = new boolean[graph.v];
        mst(graph, s);
    }

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
        LazyPrimsMST mst = new LazyPrimsMST(graph, 0);

        mst.resultedWeightedEdge.forEach(s -> System.out.println(s.from + " -- " + s.to + "\n"));
    }

    private void mst(WeightedEdgeGraph graph, int s) {
        visit(graph, s);
        while (!queue.isEmpty()) {
            final WeightedEdge edge = queue.remove();
            int from = edge.from;
            int to = edge.to;
            if (visited[to] && visited[from]) {
                continue;
            }
            if (!visited[to]) {
                visit(graph, to);
            }
            if (!visited[from]) {
                visit(graph, from);
            }
            resultedWeightedEdge.add(edge);
        }
    }

    private void visit(WeightedEdgeGraph graph, int s) {
        visited[s] = true;
        queue.addAll(graph.adjacencyList[s]);
    }

}
