package com.piyush.sahu.graph.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/alien-dictionary/
 * https://practice.geeksforgeeks.org/problems/alien-dictionary/1
 */
class AdjacencyGraph{
    int V;
    LinkedList<Integer>[] adjacencyList;
    public AdjacencyGraph(int V){
        this.V = V;
        this.adjacencyList = new LinkedList[V];
        for(int i=0; i < V; i++){
            adjacencyList[i] = new LinkedList<>();
        }
    }
    public void addEdge(int from, int to){
        adjacencyList[from].add(to);
    }
}

class TopologicalSort{
    public Stack<Integer> topologicalSort(AdjacencyGraph graph){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[graph.V];
        for(int i=0; i < graph.V; i++){
            if(!visited[i]){
                topologicalSortUtil(graph, i, stack, visited);
            }
        }
        return stack;
    }

    private void topologicalSortUtil(AdjacencyGraph graph, Integer s,
                                     Stack<Integer> stack, boolean[] visited) {
        if(visited[s]){
            return;
        }
        visited[s] = true;
        for(Integer nextNode : graph.adjacencyList[s]){
            topologicalSortUtil(graph, nextNode, stack, visited);
        }
        stack.push(s);
    }
}

public class AlienDictionary {

    public void alienOrder(String[] words) {
        AdjacencyGraph graph = new AdjacencyGraph(4);
        for(int i=0; i < words.length-1; i++){
            String word1 = words[i];
            String word2 = words[i+1];
            for(int j=0; j < Math.min(word1.length(), word2.length()); j++){
                if(word1.charAt(j) != word2.charAt(j)){
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j)-'a');
                    break;
                }
            }
        }
        TopologicalSort ts = new TopologicalSort();
        Stack<Integer> stack = ts.topologicalSort(graph);
        while(!stack.isEmpty()){
            System.out.print((char)(stack.pop() + 'a') + " ");
        }
    }

    public static void main(String[] args) {
        AlienDictionary dict = new AlienDictionary();
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        dict.alienOrder(words);
    }

}
