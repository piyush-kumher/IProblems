package com.piyush.psds.aug_6_2020_streak.leetcode.unionFind;

import java.util.ArrayList;
import java.util.List;

public class SurroundedRegions {

    public void solve_unionFind(char[][] board) {
        if(board.length == 0 || board[0].length == 0) {
            return;
        }
        int rows = board.length;
        int columns = board[0].length;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        DUS dus = new DUS(rows * columns + 1);
        int dummy = rows * columns;
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(board[i][j] == 'O') {
                    if(i==0 || j == 0 || i == rows-1 || j == columns-1) {
                        dus.union(i*columns+j, dummy);
                    } else {
                        for(int[] direction : directions) {
                            int newI = direction[0] + i;
                            int newJ = direction[1] + j;
                            if(newI >= 0 && newI < rows && newJ >= 0 && newJ < columns && board[newI][newJ] == 'O') {
                                dus.union(i*columns+j, newI*columns+newJ);
                            }
                        }
                    }
                }
            }
        }
        for(int i=1; i < rows-1; i++) {
            for(int j=0; j < columns-1; j++) {
                if(!dus.isConnected(i*columns+j, dummy)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    class DUS {
        int[] parents;
        int[] ranks;

        public DUS(int size) {
            parents = new int[size];
            ranks = new int[size];
            for(int i=0; i < size; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public int union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if(parentA == parentB) {
                return parentA;
            }
            if(ranks[parentA] > ranks[parentB]) {
                parents[parentB] = parentA;
            } else if(ranks[parentA] < ranks[parentB]) {
                parents[parentA] = parentB;
                return parentB;
            } else {
                parents[parentB] = parentA;
                ranks[parentA]++;
            }
            return parentA;
        }

        public int find(int a) {
            if(parents[a] == a) {
                return a;
            }
            return find(parents[a]);
        }

        public boolean isConnected(int a , int b) {
            return find(a) == find(b);
        }

    }

    public void solve(char[][] board) {
        if(board.length == 0 || board[0].length <= 1) {
            return;
        }
        int rows = board.length;
        int columns = board[0].length;
        List<int[]> list = new ArrayList<>();
        for(int i=0; i < rows; i++) {
            if(board[i][0] == 'O') {
                list.add(new int[]{i, 0});
            }
            if(board[i][columns-1] == 'O') {
                list.add(new int[]{i, columns-1});
            }
        }
        for(int i=0; i < columns; i++) {
            if(board[0][i] == 'O') {
                list.add(new int[]{0, i});
            }
            if(board[rows-1][i] == 'O') {
                list.add(new int[]{rows-1, i});
            }
        }
        for(int[] borderPoint : list) {
            dfs(board, borderPoint[0], borderPoint[1], rows, columns);
        }
        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if(board[i][j] == 'E') {
                    board[i][j] = 'O';
                } else if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j, int rows, int columns) {
        if(board[i][j] != 'O') {
            return;
        }
        board[i][j] = 'E';
        if(i+1 < rows) {
            dfs(board, i+1, j, rows, columns);
        }
        if(i-1 >= 0) {
            dfs(board, i-1, j, rows, columns);
        }
        if(j+1 < columns) {
            dfs(board, i, j+1, rows, columns);
        }
        if(j-1 >= 0) {
            dfs(board, i, j-1, rows, columns);
        }
    }

}
