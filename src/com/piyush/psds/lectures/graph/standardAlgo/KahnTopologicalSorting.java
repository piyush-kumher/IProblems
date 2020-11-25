package com.piyush.psds.lectures.graph.standardAlgo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
 */
class DirectedAcyclicGraph{
    int V;
    LinkedList<Integer>[] adjacencyList;
    public DirectedAcyclicGraph(int V){
        this.V = V;
        this.adjacencyList = new LinkedList[V];
        for(int i=0; i < V; i++){
            this.adjacencyList[i] = new LinkedList<>();
        }
    }
    public void addEdge(int from, int to){
        this.adjacencyList[from].add(to);
    }
}

public class KahnTopologicalSorting {

    public int[] topologicalSort(DirectedAcyclicGraph graph){
        int[] indegree = new int[graph.V];
        for(int i=0; i < graph.V; i++){
            for(int to: graph.adjacencyList[i]){
                indegree[to]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i < indegree.length; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int count = 0;
        int[] topOrder = new int[graph.V];
        while(!queue.isEmpty()){
            int node = queue.poll();
            topOrder[count++] = node;
            for(int adjNode : graph.adjacencyList[node]){
                indegree[adjNode]--;
                if(indegree[adjNode] == 0){
                    queue.add(adjNode);
                }
            }
        }
        return topOrder;
    }

    public static void main(String[] args) {
        KahnTopologicalSorting topSorting = new KahnTopologicalSorting();
//        DirectedAcyclicGraph graph = new DirectedAcyclicGraph(6);
//        graph.addEdge(5, 0);
//        graph.addEdge(4, 0);
//        graph.addEdge(5, 2);
//        graph.addEdge(4, 1);
//        graph.addEdge(2, 3);
//        graph.addEdge(3, 1);

        DirectedAcyclicGraph graph = new DirectedAcyclicGraph(2);
        graph.addEdge(1, 0);
        int [] order = topSorting.topologicalSort(graph);
        for(int i=0; i < order.length; i++){
            System.out.print(order[i] + " ");
        }
    }
}
