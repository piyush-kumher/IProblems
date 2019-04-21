package com.piyush.sahu.graph.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 */
public class CourseSchedule2 {

    /**
     * Using indegree method:
     * Runtime: 28 ms, faster than 27.99% of Java online submissions for Course Schedule II.
     * Memory Usage: 79 MB, less than 5.19% of Java online submissions for Course Schedule II.
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];
        int[] indegree = new int[numCourses];
        for(int i=0; i < prerequisites.length; i++){
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            if(matrix[src][dest] == 0){
                indegree[dest]++;
            }
            matrix[src][dest] = 1;
        }
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        int[] topOrder = new int[numCourses];
        while(!queue.isEmpty()){
            int node = queue.remove();
            topOrder[count++] = node;
            for(int i=0; i < numCourses; i++){
                if(matrix[node][i] == 1){
                    if(--indegree[i] == 0){
                        queue.add(i);
                    }
                }
            }
        }
        return count != numCourses ? new int[0] : topOrder;
    }




    public static void main(String[] args) {
        CourseSchedule2 schedule2 = new CourseSchedule2();
        int[][] prerequisites = {{1, 0}};
        schedule2.findOrder(2, prerequisites);
        schedule2.findOrderRecursive(2, prerequisites);
    }

    /**
     * Runtime: 5 ms, faster than 96.37% of Java online submissions for Course Schedule II.
     * Memory Usage: 46.1 MB, less than 37.13% of Java online submissions for Course Schedule II.
     */
    public int[] findOrderRecursive(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        Graph graph = new Graph(numCourses);
        for(int i=0; i < prerequisites.length; i++){
            graph.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        for(int i=0; i < graph.V; i++){
            if(!visited[i] && !topSort(graph, i, visited, recStack, stack)){
                return new int[0];
            }
        }
        int[] result = new int[numCourses];
        int count = 0;
        while(!stack.isEmpty()){
            result[count++] = stack.pop();
        }
        return result;
    }

    private boolean topSort(Graph graph, int node, boolean[] visited,
                            boolean[] recStack, Stack<Integer> stack) {
        visited[node] = true;
        recStack[node] = true;
        for(int edgeTo : graph.adjacencyList[node]){
            if(!visited[edgeTo]){
                if(!topSort(graph, edgeTo, visited, recStack, stack)){
                    return false;
                }
            }else if(recStack[edgeTo]){
                return false;
            }
        }
        recStack[node] = false;
        stack.push(node);
        return true;
    }

    class Graph{
        int V;
        LinkedList<Integer>[] adjacencyList;
        public Graph(int V){
            this.V = V;
            this.adjacencyList = new LinkedList[V];
            for(int i=0; i < V; i++){
                this.adjacencyList[i] = new LinkedList<>();
            }
        }
        public void addEdge(int from, int to){
            adjacencyList[from].add(to);
        }
    }

}
