package com.piyush.sahu.lectures.graph.standardAlgo;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimsMSE {

    class Node {
        int node;
        int cost;
        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    }

    class Result{
        int[] parent;
        int[] dist;
        public Result(int[] parent, int[] dist) {
            this.parent = parent;
            this.dist = dist;
        }
    }

    public Result findMSE(WeightedEdgeGraph graph, int s){
        int[] edgeTo = new int[graph.V];
        int[] dist = new int[graph.V];
        boolean[] visited = new boolean[graph.V];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        for(int i=0; i < graph.V; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        edgeTo[s] = -1;
        pq.add(new Node(s, dist[s]));
        while(!pq.isEmpty()){
            Node node = pq.remove();
            visited[node.node] = true;
            for(WeightedEdge edge : graph.adjacencyList[node.node]){
                if(!visited[edge.to]){
                    if(edge.weight < dist[edge.to]){
                        dist[edge.to] = edge.weight;
                        edgeTo[edge.to] = edge.from;
                        pq.add(new Node(edge.to, dist[edge.to]));
                    }
                }
            }
        }
        return new Result(edgeTo, dist);
    }

    public static void main(String[] args) {
        PrimsMSE mse = new PrimsMSE();
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
        Result result = mse.findMSE(graph, 0);

        System.out.println(Arrays.stream(result.dist).sum());

        for (int i = 0; i < 7; i++) {
            System.out.println((i+1) + " - " + (result.parent[i] +1) + "\t" + result.dist[i]);
        }
    }

}
