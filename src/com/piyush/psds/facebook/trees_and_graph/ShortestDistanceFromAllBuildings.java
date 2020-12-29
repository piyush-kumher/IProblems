package com.piyush.psds.facebook.trees_and_graph;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings s = new ShortestDistanceFromAllBuildings();
        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(s.shortestDistance(grid));
    }

    public int shortestDistance(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] directions = {{1,0},{0, -1},{-1, 0},{0, 1}};
        int[][] mem = new int[grid.length][grid[0].length];
        int[][] reach = new int[grid.length][grid[0].length];
        int buildings = 0;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    int distance = 1;
                    buildings++;
                    Queue<Pair<Integer, Integer>> q = new LinkedList<>();
                    boolean[][] visited = new boolean[grid.length][grid[0].length];
                    q.add(new Pair<>(i, j));
                    visited[i][j] = true;
                    while(!q.isEmpty()) {
                        int size = q.size();
                        for(int x=0; x < size; x++) {
                            Pair<Integer, Integer> p = q.poll();
                            int m = p.getKey();
                            int n = p.getValue();
                            for(int[] dir : directions) {
                                int newM = m + dir[0];
                                int newN = n + dir[1];
                                if(newM >= 0 && newM < grid.length && newN >= 0 && newN < grid[0].length && grid[newM][newN] == 0 && !visited[newM][newN]) {
                                    visited[newM][newN] = true;
                                    reach[newM][newN]++;
                                    mem[newM][newN] += distance;
                                    q.add(new Pair<>(newM, newN));
                                }
                            }
                        }
                        distance++;
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0; i < grid.length; i++) {
            for(int j=0; j < grid[0].length; j++) {
                if(grid[i][j] == 0 && reach[i][j] == buildings) {
                    min = Math.min(min, mem[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
