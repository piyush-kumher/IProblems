package com.piyush.psds.lectures.graph.standardAlgo;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Topological sort is reverse of DFS
 */

class AdjancencyListGraph{
    LinkedList<Integer>[] adjancencyList;
    int numberOfNodes;

    public AdjancencyListGraph(int numberOfNodes){
        this.numberOfNodes = numberOfNodes;
        adjancencyList = new LinkedList[numberOfNodes];
        for(int i=0; i < numberOfNodes; i++){
            adjancencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to){
        adjancencyList[from].push(to);
    }
}

public class TopologicalSort {


    public static void topologicalSort(AdjancencyListGraph graph){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.numberOfNodes];
        for(int i=0; i< graph.numberOfNodes; i++){
            if(!visited[i]) {
                topologicalSortUtil(graph, i, stack, visited);
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
        stack = new Stack<>();
        visited = new boolean[graph.numberOfNodes];
        for(int i=graph.numberOfNodes-1; i >= 0; i--){
            if(!visited[i]) {
                topologicalSortUtil(graph, i, stack, visited);
            }
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop()  + " ");
        }
    }

    private static void topologicalSortUtil(AdjancencyListGraph graph, int node,
                                     Stack<Integer> stack, boolean[] visited) {
        LinkedList<Integer> nextNodes = graph.adjancencyList[node];
        visited[node] = true;
        for(Integer nextNode : nextNodes){
            if(!visited[nextNode]){
                topologicalSortUtil(graph, nextNode, stack, visited);
            }
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        AdjancencyListGraph graph = new AdjancencyListGraph(6);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(5, 2);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        topologicalSort(graph);

    }

}
