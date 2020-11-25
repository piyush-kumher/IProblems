package com.piyush.psds.aug_6_2020_streak.graph.mst;

import com.piyush.psds.aug_6_2020_streak.graph.union_find.UnionFind;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdge;
import com.piyush.psds.aug_6_2020_streak.graph.entities.WeightedEdgeGraph;

import java.util.*;

public class KrushkalMST {

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
        KrushkalMST mst = new KrushkalMST();
        final List<WeightedEdge> rl = mst.mst(graph);
        int total = 0;
        for(WeightedEdge e : rl) {
            total += e.weight;
            System.out.println("from:" + e.from + " ,to=" + e.to + " ,dist=" + e.weight);
        }
        System.out.println("TOTAL: " + total);
    }

    private List<WeightedEdge> mst(WeightedEdgeGraph graph) {
        List<WeightedEdge> resultList = new ArrayList<>();
        PriorityQueue<WeightedEdge> q = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
        UnionFind uf = new UnionFind(graph.v);
        for (LinkedList<WeightedEdge> list : graph.adjacencyList) {
            q.addAll(list);
        }
        while (!q.isEmpty()) {
            final WeightedEdge ele = q.remove();
            boolean cycle = uf.union(ele.from, ele.to);
            if (!cycle) {
                resultList.add(ele);
            }
        }
        return resultList;
    }

}
