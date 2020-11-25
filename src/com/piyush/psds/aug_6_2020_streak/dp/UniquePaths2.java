package com.piyush.psds.aug_6_2020_streak.dp;

//https://leetcode.com/problems/unique-paths-ii/
public class UniquePaths2 {

    public static void main(String[] args) {
        UniquePaths2 u = new UniquePaths2();
//        int[][] arr = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] arr = {{0,0}};
        System.out.println(u.uniquePathsWithObstacles(arr));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length == 0) {
            return 0;
        }
        int[][] mat = new int[obstacleGrid.length][obstacleGrid[0].length];
        boolean pathExisits = true;
        for(int i=0; i < obstacleGrid.length; i++) {
            if(obstacleGrid[i][0] != 1) {
                pathExisits = false;
            }
            mat[i][0] = pathExisits ? 1 : 0;
        }
        pathExisits = true;
        for(int i=0; i < obstacleGrid[0].length; i++) {
            if(obstacleGrid[0][i] != 1) {
                pathExisits = false;
            }
            mat[0][i] = pathExisits ? 1 : 0;
        }
        for(int i=1; i < obstacleGrid.length; i++) {
            for(int j=1; j < obstacleGrid[0].length; j++) {
                if(obstacleGrid[i][j] == 1) {
                    mat[i][j] = 0;
                }  else {
                    mat[i][j] = mat[i-1][j] + mat[i][j-1];
                }
            }
        }
        return mat[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
