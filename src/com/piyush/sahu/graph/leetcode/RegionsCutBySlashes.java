package com.piyush.sahu.graph.leetcode;

/**
 * Regions Cut By Slashes
 * https://leetcode.com/problems/regions-cut-by-slashes/
 */
public class RegionsCutBySlashes {

    /**
     * Runtime: 5 ms, faster than 95.86% of Java online submissions for Regions Cut By Slashes.
     * Memory Usage: 38.6 MB, less than 27.42% of Java online submissions for Regions Cut By Slashes.
     */
    public int regionsBySlashes(String[] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int n = grid.length;
        UnionFind uf = new UnionFind(n*n*4);
        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                if(i > 0){
                    uf.union(index(i-1, j, 2, n), index(i, j, 0, n));
                }
                if(j > 0){
                    uf.union(index(i, j-1, 1, n), index(i, j, 3, n));
                }
                if(grid[i].charAt(j) != '\\'){
                    uf.union(index(i, j, 0, n), index(i, j, 3, n));
                    uf.union(index(i, j, 1, n), index(i, j, 2, n));
                }
                if(grid[i].charAt(j) != '/'){
                    uf.union(index(i, j, 0, n), index(i, j, 1, n));
                    uf.union(index(i, j, 2, n), index(i, j, 3, n));
                }
            }
        }
        return uf.getCount();
    }

    private int index(int i, int j, int k, int n){
        return (i*n+j)*4 + k;
    }

    /**
     * Explanation:
     * https://leetcode.com/problems/regions-cut-by-slashes/discuss/205674/C%2B%2B-with-picture-DFS-on-upscaled-grid
     * Runtime: 7 ms, faster than 86.76% of Java online submissions for Regions Cut By Slashes.
     * Memory Usage: 37.7 MB, less than 70.97% of Java online submissions for Regions Cut By Slashes.
     */
    public int regionsBySlashes_DFS(String[] grid){
        if(grid == null || grid.length == 0){
            return 0;
        }
        int n = grid.length;
        int[][] new_grid = new int[n*3][n*3];
        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                if(grid[i].charAt(j) == '/'){
                    new_grid[i*3][j*3+2] = new_grid[i*3+1][j*3+1] = new_grid[i*3+2][j*3] = 1;
                }if(grid[i].charAt(j) == '\\'){
                    new_grid[i*3][j*3] = new_grid[i*3+1][j*3+1] = new_grid[i*3+2][j*3+2] = 1;
                }
            }
        }
        int regions = 0;
        for(int i=0; i < n*3; i++){
            for(int j=0; j < n*3; j++){
                if(new_grid[i][j] == 0){
                    dfs(new_grid, i, j, n*3);
                    regions++;
                }
            }
        }
        return regions;
    }

    private void dfs(int[][] grid, int i, int j, int n){
        if(i >=0 && j >= 0 && i < n && j < n && grid[i][j] == 0){
            grid[i][j] = 1;
            dfs(grid, i-1, j, n);
            dfs(grid, i+1, j, n);
            dfs(grid, i, j-1, n);
            dfs(grid, i, j+1, n);
        }
    }

    class UnionFind{
        int[] parent;
        int[] rank;
        int count = 0;
        UnionFind(int N){
            count = N;
            parent = new int[N];
            rank = new int[N];
            for(int i=0; i < N; i++){
                parent[i] = i;
            }
        }
        private int find(int p){
            if(parent[p] == p){
                return p;
            }
            return find(parent[p]);
        }
        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ){
                return;
            }
            if(rank[rootP] < rank[rootQ]){
                parent[rootP] = rootQ;
            }else if(rank[rootP] > rank[rootQ]){
                parent[rootQ] = rootP;
            }else{
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }
        public int getCount(){
            return count;
        }
    }

}
