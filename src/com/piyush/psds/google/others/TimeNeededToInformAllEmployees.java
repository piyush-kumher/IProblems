package com.piyush.psds.google.others;

import java.util.*;

public class TimeNeededToInformAllEmployees {

    public static void main(String[] args) {
        TimeNeededToInformAllEmployees t = new TimeNeededToInformAllEmployees();
        int n = 11;
        int headId = 4;
        int[] manager = {5,9,6,10,-1,8,9,1,9,3,4};
        int[] informTime = {0,213,0,253,686,170,975,0,261,309,337};
        System.out.println(t.numOfMinutes(n, headId, manager, informTime));
    }

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(headID, new ArrayList<>());
        for(int i=0; i < n; i++) {
            if(manager[i] != -1) {
                graph.putIfAbsent(manager[i], new ArrayList<>());
                graph.get(manager[i]).add(i);
            }
        }
        // Queue<Integer> q = new LinkedList<>();
        // q.add(headID);
        // int result = 0;
        // while(!q.isEmpty()) {
        //     int size = q.size();
        //     int max = 0;
        //     for(int i=0; i < size; i++) {
        //         int emp = q.remove();
        //         max = Math.max(max, informTime[emp]);
        //         for(int next : graph.getOrDefault(emp, new ArrayList<>())) {
        //             q.offer(next);
        //         }
        //     }
        //     result += max;
        // }
        return dfs(graph, headID, informTime);
    }

    private int dfs(Map<Integer, List<Integer>> graph, int nodeId, int[] informTime) {
        int max = 0;
        for(int next : graph.getOrDefault(nodeId, new ArrayList<>())) {
            if(graph.containsKey(next)) {
                max = Math.max(max, dfs(graph, next, informTime));
            }
        }
        return max + informTime[nodeId];
    }

}
