package com.piyush.psds.uber;

// https://leetcode.com/problems/minesweeper/
public class MineSweeper {

    int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {-1,-1}, {1,-1},{-1,1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0) {
            return new char[0][0];
        }
        int rows = board.length;
        int columns = board[0].length;
        click(board, rows, columns, click);
        return board;
    }

    private void click(char[][] board, int rows, int columns, int[] click) {
        int i = click[0];
        int j = click[1];
        if(board[i][j] == 'M') {
            board[i][j] = 'X';
        } else if(board[i][j] == 'E') {
            int mineCount = getNumberOfMines(board, i, j, rows, columns);
            if(mineCount == 0) {
                board[i][j] = 'B';
                for(int k=0; k < directions.length; k++) {
                    int m = i + directions[k][0];
                    int n = j + directions[k][1];
                    if(m < rows && m >=0 && n < columns && n >= 0) {
                        if(board[m][n] == 'E') {
                            int[] nextClick = new int[]{m,n};
                            click(board, rows, columns, nextClick);
                        }
                    }
                }
            } else {
                board[i][j] = (char) (mineCount + '0');
            }
        }
    }

    private int getNumberOfMines(char[][] board, int i, int j, int rows, int columns) {
        int count = 0;
        for(int k=0; k < directions.length; k++) {
            int m = i + directions[k][0];
            int n = j + directions[k][1];
            if(m < rows && m >=0 && n < columns && n >= 0) {
                if(board[m][n] == 'X' || board[m][n] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }

}
