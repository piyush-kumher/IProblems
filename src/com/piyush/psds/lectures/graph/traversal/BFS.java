package com.piyush.psds.lectures.graph.traversal;

import com.piyush.psds.lectures.graph.entities.AdjacencyListGraph;
import com.piyush.psds.lectures.graph.entities.AdjacencyMatrixGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void usingAdjacencyList(AdjacencyListGraph<Integer> graph, Integer startNode){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        while(!queue.isEmpty()){
            Integer node = queue.remove();
            LinkedList<Integer> list = graph.getAdjacencyList()[node];
            visited[node] = true;
            for(Integer nextNodes: list){
                if(!visited[nextNodes]) {
                    queue.add(nextNodes);
                }
            }
            System.out.print(node + " ");
        }
    }

    public static void usingAdjacencyMatrix(AdjacencyMatrixGraph graph, Integer startNode){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        boolean[] visited = new boolean[graph.getNumberOfVertices()];
        while(!queue.isEmpty()){
            Integer node = queue.remove();
            int[] list = graph.getAdjacencyMatrix()[node];
            visited[node] = true;
            for(int i=0; i<list.length; i++){
                if(list[i] == 1 && !visited[i]) {
                    queue.add(i);
                }
            }
            System.out.print(node + " ");
        }
    }

    public static void main(String[] args){
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(9);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(5, 1);
        graph.addEdge(5, 7);
        graph.addEdge(5, 8);
        graph.addEdge(6, 3);
        usingAdjacencyList(graph, 0);

        System.out.println("");

        AdjacencyMatrixGraph matGraph = new AdjacencyMatrixGraph(9);
        matGraph.addEdge(0, 1);
        matGraph.addEdge(0, 2);
        matGraph.addEdge(0, 3);
        matGraph.addEdge(1, 4);
        matGraph.addEdge(2, 5);
        matGraph.addEdge(2, 6);
        matGraph.addEdge(5, 1);
        matGraph.addEdge(5, 7);
        matGraph.addEdge(5, 8);
        matGraph.addEdge(6, 3);
        usingAdjacencyMatrix(matGraph, 0);

    }

}
