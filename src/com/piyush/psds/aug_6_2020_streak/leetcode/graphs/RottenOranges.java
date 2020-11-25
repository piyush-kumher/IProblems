package com.piyush.psds.aug_6_2020_streak.leetcode.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        RottenOranges ro = new RottenOranges();
        System.out.println(ro.orangesRotting(grid));
    }

    public int orangesRotting(int[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        Queue<int[]> q = new ArrayDeque<>();
        int row = grid.length;
        int column = grid[0].length;
        int fresh = 0;
        for(int i=0; i < row; i++) {
            for(int j=0; j < column; j++) {
                if(grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                } else if(grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int[][] directions = {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        int minutes = -1;
        q.offer(new int[]{-1, -1});
        while(!q.isEmpty()) {
            int[] point = q.poll();
            int r = point[0];
            int c = point[1];
            if(r == -1) {
                minutes++;
                if(!q.isEmpty()) {
                    q.offer(new int[]{-1, -1});
                }
            } else {
                for (int[] direction : directions) {
                    int i = r + direction[0];
                    int j = c + direction[1];
                    if (i >= 0 && i < row && j >= 0 && j < column && grid[i][j] == 1) {
                        fresh--;
                        grid[i][j] = 2;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }
        if(fresh > 0) {
            return -1;
        }
        return minutes;
    }

}
