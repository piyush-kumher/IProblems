package com.piyush.psds.graph.leetcode;

/**
 * https://leetcode.com/problems/number-of-islands/submissions/
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 */
public class NumberOfIslands {

    /**
     * Runtime: 2 ms, faster than 91.15% of Java online submissions for Number of Islands.
     * Memory Usage: 42.2 MB, less than 5.02% of Java online submissions for Number of Islands.
     *
     * Consider each 1 and 0 a node..
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        for(int i=0; i < parent.length; i++){
            parent[i] = i;
        }
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j] == '0'){
                    continue;
                }
                if(j+1 < n && grid[i][j+1] == '1'){
                    union(i*n + j, i*n + j+1, parent, rank);
                }
                if(j-1 >= 0 && grid[i][j-1] == '1'){
                    union(i*n + j, i*n + j-1, parent, rank);
                }
                if(i+1 < m && grid[i+1][j] == '1'){
                    union(i*n + j, (i+1)*n + j, parent, rank);
                }
                if(i-1 >= 0 && grid[i-1][j] == '1'){
                    union(i*n + j, (i-1)*n + j, parent, rank);
                }
            }
        }
        int[] count = new int[m*n];
        int numberOfIslands = 0;
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j] == '1'){
                    int root = find(i*n + j, parent);
                    if(count[root] == 0){
                        count[root]++;
                        numberOfIslands++;
                    }else{
                        count[root]++;
                    }
                }
            }
        }
        return numberOfIslands;
    }

    private int find(int a, int[] parent){
        if(a == parent[a]){
            return a;
        }
        return find(parent[a], parent);
    }

    private void union(int a, int b, int[] parent, int[] rank){
        int rootA = find(a, parent);
        int rootB = find(b, parent);
        if(rootA == rootB){
            return;
        }
        if(rank[rootA] > rank[rootB]){
            parent[rootB] = rootA;
        }else if(rank[rootA] < rank[rootB]){
            parent[rootA] = rootB;
        }else{
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }


    /**
     * Runtime: 2 ms, faster than 91.15% of Java online submissions for Number of Islands.
     * Memory Usage: 41 MB, less than 30.36% of Java online submissions for Number of Islands.
     */
    public int numIslandsUsingDFS(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(!visited[i][j] && grid[i][j] == '1'){
                    dfs(i , j, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, char[][] grid, boolean[][] visited){
        int m=grid.length;
        int n=grid[0].length;
        visited[i][j] = true;
        if(j+1 < n && grid[i][j+1] == '1'){
            if(!visited[i][j+1]) {
                dfs(i, j + 1, grid, visited);
            }
        }
        if(j-1 >= 0 && grid[i][j-1] == '1'){
            if(!visited[i][j-1]) {
                dfs(i, j - 1, grid, visited);
            }
        }
        if(i+1 < m && grid[i+1][j] == '1'){
            if(!visited[(i+1)][j]) {
                dfs(i+1, j, grid, visited);
            }
        }
        if(i-1 >= 0 && grid[i-1][j] == '1'){
            if(!visited[(i-1)][j]) {
                dfs(i-1, j, grid, visited);
            }
        }
    }



    public static void main(String[] args) {
        //char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},
        //        {'0','0','0','0','0'}};
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        NumberOfIslands noi = new NumberOfIslands();
        System.out.println(noi.numIslands(grid));
        System.out.println(noi.numIslandsUsingDFS(grid));
    }

}
