package com.piyush.psds.lectures.graph.traversal;

import com.piyush.psds.lectures.graph.entities.AdjacencyListGraph;
import com.piyush.psds.lectures.graph.entities.AdjacencyMatrixGraph;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {

    public static void usingStackAndList(AdjacencyListGraph<Integer> graph, int startNode){
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        while(!stack.isEmpty()){
            Integer node = stack.pop();
            LinkedList<Integer> list = graph.getAdjacencyList()[node];
            if(!visited[node]){
                System.out.print(node + " ");
            }
            visited[node] = true;
            for(Integer nextNode : list){
                if(!visited[nextNode]){
                    stack.push(nextNode);
                }
            }
        }
    }

    public static void usingStackAndMatrix(AdjacencyMatrixGraph graph, int startNode){
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        boolean[] visited = new boolean[graph.getNumberOfVertices()];
        while(!stack.isEmpty()){
            Integer node = stack.pop();
            if(!visited[node]){
                System.out.print(node + " ");
            }
            visited[node] = true;
            int[] nextNodes = graph.getAdjacencyMatrix()[node];
            for(int i=0; i< nextNodes.length; i++){
                if(nextNodes[i] == 1 && !visited[i]){
                    stack.push(i);
                }
            }
        }
    }

    public static void usingRecusrsion(AdjacencyListGraph<Integer> graph, Integer node, boolean[] visited){
        System.out.print(node + " ");
        visited[node] = true;
        LinkedList<Integer> list = graph.getAdjacencyList()[node];
        for(int nextNode : list){
            if(!visited[nextNode]) {
                usingRecusrsion(graph, nextNode, visited);

            }
        }
    }

    public static void allDFS(AdjacencyListGraph<Integer> graph){
        boolean[] visited = new boolean[graph.getNoOfVertices()];
        for(int i=0; i < visited.length; i++){
            if(!visited[i]){
                usingRecusrsion(graph, i, visited);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
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
        usingStackAndList(graph, 0);
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
        usingStackAndMatrix(matGraph, 0);
        System.out.println();

        AdjacencyListGraph<Integer> directedGraph = new AdjacencyListGraph<>(8);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(0, 3);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(1, 0);
        directedGraph.addEdge(1, 4);
        directedGraph.addEdge(3, 4);
        directedGraph.addEdge(3, 6);
        directedGraph.addEdge(5, 4);
        directedGraph.addEdge(7, 4);
        directedGraph.addEdge(7, 6);
        boolean[] visited = new boolean[directedGraph.getNoOfVertices()];
        usingRecusrsion(directedGraph, 0, visited);
        System.out.println();
        visited = new boolean[directedGraph.getNoOfVertices()];
        usingRecusrsion(directedGraph, 1, visited);
        System.out.println();

        allDFS(directedGraph);
    }

}
