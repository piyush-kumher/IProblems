package com.piyush.psds.graph.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        boolean cycleExists = cycleExists(graph);
        return !cycleExists;
    }

    public boolean cycleExists(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        boolean[] recStack = new boolean[graph.V];
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i]) {
                if (cycleDetection(graph, i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Second approach: Using Kahn Algo: BFS extended
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishNew(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[][] matrix = new int[numCourses][numCourses];
        for(int i=0; i < prerequisites.length; i++){
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if(matrix[pre][ready] == 0){
                indegree[ready]++;
            }
            matrix[pre][ready] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        while(!queue.isEmpty()){
            int node = queue.poll();
            count++;
            for(int j=0; j < numCourses; j++){
                if(matrix[node][j] == 1){
                    if(--indegree[j] == 0){
                        queue.add(j);
                    }
                }
            }
        }
        return (count == numCourses);
    }

    private boolean cycleDetection(Graph graph, int node, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) {
            return true;
        }
        if (visited[node]) {
            return false;
        }
        visited[node] = true;
        recStack[node] = true;
        for (int nextNode : graph.adjacencyList[node]) {
            if (cycleDetection(graph, nextNode, visited, recStack)) {
                return true;
            }
        }
        recStack[node] = false;
        return false;
    }

    class Graph {
        int V;
        LinkedList<Integer>[] adjacencyList;

        public Graph(int V) {
            this.V = V;
            this.adjacencyList = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int from, int to) {
            adjacencyList[from].add(to);
        }

    }

}
