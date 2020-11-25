package com.piyush.psds.aug_6_2020_streak.dp;

public class MaximumSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximumSquare ms = new MaximumSquare();
        System.out.println(ms.maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i][j + 1], dp[i + 1][j]), dp[i][j]) + 1;
                    max = Math.max(dp[i + 1][j + 1], max);
                }
            }
        }
        //print(dp);
        return max * max;
    }

    public int maximalSquare_1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int max = 0;
        int prev = 0;
        int[] dp = new int[matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int temp = dp[j+1];
                if (matrix[i][j] == '1') {
                    dp[j + 1] = Math.min(Math.min(dp[j + 1], prev), dp[j]) + 1;
                    max = Math.max(dp[j + 1], max);
                } else {
                    dp[j+1] = 0;
                }
                prev = temp;
            }
        }
        //print(dp);
        return max * max;
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
