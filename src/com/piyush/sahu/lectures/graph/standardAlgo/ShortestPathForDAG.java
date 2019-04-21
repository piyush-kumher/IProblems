package com.piyush.sahu.lectures.graph.standardAlgo;

import com.piyush.sahu.lectures.graph.entities.DirectedEdge;
import com.piyush.sahu.lectures.graph.entities.WeightedDirectedGraph;

import java.util.Stack;

/**
 * complexity: O(E+V)
 */
public class ShortestPathForDAG {


    public static Stack<Integer> topologicalSort(WeightedDirectedGraph graph){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.getV()];
        for(int i=0; i < graph.getV(); i++){
            if(!visited[i]){
                topologicalUtil(graph, i, stack, visited);
            }
        }
        return stack;
    }

    private static void topologicalUtil(WeightedDirectedGraph graph, int node,
                                        Stack<Integer> stack, boolean[] visited) {
        visited[node] = true;
        for(DirectedEdge nextNode: graph.getAdjacencyList(node)){
            if(!visited[nextNode.getV()]){
                topologicalUtil(graph, nextNode.getV(), stack, visited);
            }
        }
        stack.push(node);
    }

    public static void printStack(Stack<Integer> stack){
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }

    static class ShortestDAGPaths{
        double[] dist;
        Integer[] ancestor;
        ShortestDAGPaths(double[] dist, Integer[] ancestor){
            this.dist = dist;
            this.ancestor =ancestor;
        }
    }

    public static ShortestDAGPaths findShortestPath(Stack<Integer> stack, WeightedDirectedGraph graph, int node){
        double[] dist = new double[graph.getV()];
        Integer[] ancestor = new Integer[graph.getV()];
        for(int i=0; i < graph.getV(); i++){
            dist[i] = Double.MAX_VALUE;
        }
        dist[node] = 0;
        while(!stack.isEmpty()){
            Integer currNode = stack.pop();
            for(DirectedEdge edge : graph.getAdjacencyList(currNode)){
                if(dist[edge.getV()] > dist[edge.getU()] + edge.getWeight()){
                    dist[edge.getV()] = dist[edge.getU()] + edge.getWeight();
                    ancestor[edge.getV()] = edge.getU();
                }
            }
        }
        return new ShortestDAGPaths(dist, ancestor);
    }

    public static void printPathToV(ShortestDAGPaths paths, int s, int v){
        System.out.println("Distance is: " + paths.dist[v]);
        int element = v;
        if(paths.dist[v] == Double.MAX_VALUE){
            System.out.println("Path does not exists from " + s + " to: " + v );
        }else {
            while (element != s) {
                System.out.print(element + "-->");
                element = paths.ancestor[element];
            }
            System.out.println(element);
        }
    }


    public static void main(String[] args) {
        WeightedDirectedGraph graph = new WeightedDirectedGraph(6);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 6);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, -2);

//        graph.addEdge(0, 1, 5);
//        graph.addEdge(0, 2, 3);
//        graph.addEdge(1, 3, 6);
//        graph.addEdge(1, 2, 2);
//        graph.addEdge(2, 4, 4);
//        graph.addEdge(2, 5, 2);
//        graph.addEdge(2, 3, 7);
//        graph.addEdge(3, 4, -1);
//        graph.addEdge(4, 5, -2);

        Stack<Integer> stack = topologicalSort(graph);
        ShortestDAGPaths paths = findShortestPath(stack, graph, 1);
        for(int i=0; i < graph.getV(); i++) {
            printPathToV(paths, 1, i);
        }
    }

}
