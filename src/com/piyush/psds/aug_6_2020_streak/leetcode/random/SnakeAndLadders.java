package com.piyush.psds.aug_6_2020_streak.leetcode.random;

import java.util.*;

// https://leetcode.com/problems/snakes-and-ladders/submissions/
public class SnakeAndLadders {

    public int snakesAndLadders(int[][] board) {
        if(board.length == 0) {
            return -1;
        }
        int n = board.length;
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        map.put(1, 0);
        while(!queue.isEmpty()) {
            int box = queue.remove();
            if(box == n*n) {
                return map.get(box);
            }
            for(int i=box+1; i <= Math.min(box+6, n*n); i++) {
                int index = getIJ(i, n);
                int row = index / n;
                int column = index % n;
                int end = board[row][column] == -1 ? i : board[row][column];
                if(!map.containsKey(end)) {
                    map.put(end, map.get(box) + 1);
                    queue.offer(end);
                }
            }
        }
        return -1;
    }

    private int getIJ(int s, int n) {
        int quot = (s-1) / n;
        int rem = (s-1) % n;
        int i = (n - 1) - quot;
        int j = i % 2 != n % 2 ? rem : n-1-rem;
        return i*n+j;
    }

}
