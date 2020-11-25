package com.piyush.psds.graph.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Ex: Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * Output: [1,1,2,3]
 *
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 *
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 *
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 *
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 */
public class NumberOfIsland2 {

    /**
     * Runtime: 89 ms, faster than 18.32% of Java online submissions for Number of Islands II.
     * Memory Usage: 60 MB, less than 11.86% of Java online submissions for Number of Islands II.
     */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        UnionFind uf = new UnionFind(m*n);
        for(int i=0; i < positions.length; i++){
            int row = positions[i][0], column = positions[i][1];
            uf.setParent(row * n + column);
            if(row-1 >= 0 && uf.isValid((row-1) * n + (column))){
                uf.union((row-1) * n + column, row * n + column);
            }
            if(row+1 < m && uf.isValid((row+1) * n + (column))){
                uf.union((row+1) * n + column, row * n + column);
            }
            if(column-1 >= 0 && uf.isValid((row) * n + (column-1))){
                uf.union(row * n + column-1, row * n + column);
            }
            if(column+1 < n && uf.isValid((row) * n + (column+1))){
                uf.union(row * n + column+1, row * n + column);
            }
            result.add(uf.getCount());

        }
        return result;
    }

    class UnionFind{
        int[] parent;
        int[] rank;
        int count=0;

        UnionFind(int m){
            parent = new int[m];
            rank = new int[m];
            for(int i=0; i < m; i++){
                parent[i] = -1;
            }
        }

        public void union(int p, int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ){
                return;
            }else if(rank[rootP] < rank[rootQ]){
                parent[rootQ] = rootP;
            }else if(rank[rootP] > rank[rootQ]){
                parent[rootP] = rootQ;
            }else{
                parent[rootQ] = rootP;
                rank[rootP] = rank[rootP]+1;
            }
            count--;
        }

        private int find(int p){
            if(parent[p] == p){
                return p;
            }
            return find(parent[p]);
        }

        public void setParent(int i) {
            parent[i] = i;
            count++;
        }

        private boolean isValid(int p){
            return parent[p] >= 0;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        NumberOfIsland2 n = new NumberOfIsland2();
        int[][] positions = {{0,0},{0,1},{1,2},{2,1}};
        n.numIslands2(3, 3, positions);
    }
}
