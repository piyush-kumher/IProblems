package com.piyush.psds.aug_6_2020_streak.dp;

// https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {1, 1}};
        MinimumPathSum mps = new MinimumPathSum();
        System.out.println(mps.minPathSum(arr));
    }


    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] minPathSum = new int[n];
        for (int i = 0; i < m; i++) {
            int prev = 0;
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    minPathSum[j] = prev + grid[i][j];
                } else if (j == 0) {
                    minPathSum[j] = minPathSum[j] + grid[i][j];
                } else {
                    minPathSum[j] = Math.min(prev, minPathSum[j]) + grid[i][j];
                }
                prev = minPathSum[j];
            }
        }
        return minPathSum[n - 1];
    }

    public int minPathSum_2(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] minPathSum = new int[m][n];
        minPathSum[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            minPathSum[i][0] = minPathSum[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            minPathSum[0][j] = minPathSum[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                minPathSum[i][j] = Math.min(minPathSum[i - 1][j], minPathSum[i][j - 1]) + grid[i][j];
            }
        }
        //print(minPathSum, m ,n);
        return minPathSum[m - 1][n - 1];
    }

    private void print(int[][] minPathSum, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(minPathSum[i][j] + " ");
            }
            System.out.println();
        }
    }

}
