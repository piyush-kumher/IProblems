package com.piyush.sahu.lectures.graph.standardAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class WeightedEdge{
    int weight;
    int from;
    int to;
    WeightedEdge(int from, int to, int weight){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}

class WeightedEdgeGraph{
    int V;
    int E;
    LinkedList<WeightedEdge>[] adjacencyList;
    WeightedEdgeGraph(int V){
        this.V = V;
        this.adjacencyList = new LinkedList[V];
        for(int i=0; i < V; i++){
            adjacencyList[i] = new LinkedList<>();
        }
    }
    public void addEdge(int from, int to, int weight){
        adjacencyList[from].add(new WeightedEdge(from, to, weight));
        E++;
    }
    public WeightedEdge[] edges(){
        WeightedEdge[] edges = new WeightedEdge[E];
        int count = 0;
        for(LinkedList<WeightedEdge> edgeList : adjacencyList){
            for(WeightedEdge edge : edgeList){
                edges[count++] = edge;
            }
        }
        return edges;
    }
}

public class KrushkalMSE {
    public Result findMSE(WeightedEdgeGraph graph){
        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.weight));
        WeightedEdge[] edges = graph.edges();
        UnionFind uf = new UnionFind(graph.V);
        List<WeightedEdge> list = new ArrayList<>();
        int mstWeights = 0;
        pq.addAll(Arrays.asList(edges));
        while(!pq.isEmpty()){
            WeightedEdge edge = pq.remove();
            if(!uf.union(edge.from, edge.to)){
                list.add(edge);
                mstWeights = mstWeights+edge.weight;
            }
        }
        return new Result(list, mstWeights);
    }

    class Result{
        List<WeightedEdge> mstEdges;
        int mstWeights;
        public Result(List<WeightedEdge> mstEdges, int mstWeights) {
            this.mstEdges = mstEdges;
            this.mstWeights = mstWeights;
        }
    }

    public static void main(String[] args) {
        KrushkalMSE mse = new KrushkalMSE();
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
        Result result = mse.findMSE(graph);
        for(WeightedEdge edge: result.mstEdges){
            System.out.println((edge.from + 1) + " --> " + (edge.to + 1));
        }
        System.out.println("Total Weight: " + result.mstWeights);
    }
}
