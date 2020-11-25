package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

public class CourseSchedule2 {

    public static void main(String[] args) {
        CourseSchedule2 cs = new CourseSchedule2();
        int[][] prerequisites = {{1,0},{0,1}};
        System.out.println(cs.findOrder(2, prerequisites));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph g = new Graph(numCourses);
        for(int i=0; i < prerequisites.length; i++) {
            g.addEdge(prerequisites[i][1], prerequisites[i][0]);
        }
        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> visited = new HashMap<>();
        for(int i=0; i < numCourses; i++) {
            if(!visited.containsKey(i) && !topologicalSortUtil(g, i, stk, visited)) {
                return new int[0];
            }
        }
        int[] result = new int[numCourses];
        int count = 0;
        while(!stk.isEmpty()) {
            result[count++] = stk.pop();
        }
        return result;
    }

    private boolean topologicalSortUtil(Graph g, int i, Stack<Integer> stk, Map<Integer, Integer> visited) {
        visited.put(i, 1);
        for(int next : g.adjList[i]) {
            if(visited.containsKey(next) && visited.get(next) == 1) {
                return false;
            } else if(!visited.containsKey(next)) {
                topologicalSortUtil(g, next, stk, visited);
            }
        }
        visited.put(i, 2);
        stk.push(i);
        return true;
    }

    class Graph {
        LinkedList<Integer>[] adjList;
        public Graph(int size) {
            this.adjList = new LinkedList[size];
            for(int i=0; i < size; i++) {
                adjList[i] = new LinkedList<>();
            }
        }
        public void addEdge(int i, int j) {
            adjList[i].add(j);
        }
    }
}
