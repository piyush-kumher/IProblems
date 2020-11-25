package com.piyush.psds.graph.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    /*
        Using Bellman Ford shortest path formula
        Runtime: 34 ms, faster than 72.01% of Java online submissions for Network Delay Time.
        Memory Usage: 47.2 MB, less than 88.56% of Java online submissions for Network Delay
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N];
        for(int i=0; i < N; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K-1] = 0;
        for(int i=0; i < N-1; i++){
            for(int[] edge : times){
                if(dist[edge[0]-1] != Integer.MAX_VALUE && dist[edge[1]-1] > dist[edge[0]-1] + edge[2]){
                    dist[edge[1]-1] = dist[edge[0]-1] + edge[2];
                }
            }
        }
        int max = 0;
        for(int i=0; i < N; i++){
            System.out.println(dist[i]);
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            if(max < dist[i]){
                max = dist[i];
            }
        }
        return max;
    }

    public int networkDelayTimeUsingDijkstraAlgo(int[][] times, int N, int K) {
        int[] dist = new int[N];
        PriorityQueue<Node> queue = new PriorityQueue<>(N, new Node());
        List<Node>[] graph = new ArrayList[N];
        for(int i=0; i < N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] time : times){
            graph[time[0]-1].add(new Node(time[1]-1, time[2]));
        }
        boolean[] setteled = new boolean[N];
        for(int i=0; i < N; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[K-1] = 0;
        queue.add(new Node(K-1, 0));
        while(!queue.isEmpty()){
            Node node = queue.remove();
            setteled[node.node] = true;
            for(Node nextNode : graph[node.node]){
                if(!setteled[nextNode.node] && dist[nextNode.node] > dist[node.node] + nextNode.weight){
                    dist[nextNode.node] = dist[node.node] + nextNode.weight;
                    queue.add(new Node(nextNode.node, dist[nextNode.node]));
                }
            }
        }
        int max = 0;
        for(int i=0; i < N; i++){
            System.out.println(dist[i]);
            if(dist[i] == Integer.MAX_VALUE){
                return -1;
            }
            if(max < dist[i]){
                max = dist[i];
            }
        }
        return max;
    }

    class Node implements Comparator<Node> {
        int node;
        int weight;

        public Node(){

        }
        public Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }
        @Override
        public int compare(Node n1, Node n2) {
            if(n1.weight < n2.weight){
                return -1;
            }else if(n1.weight > n2.weight){
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        NetworkDelayTime ndt = new NetworkDelayTime();
        int[][] times = {{1,2,1},{2,1,3}};
        System.out.println(ndt.networkDelayTimeUsingDijkstraAlgo(times, 2, 2));
    }
}
