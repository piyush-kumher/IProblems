package com.piyush.psds.graph.leetcode;

public class SurroundedRegion {

    /**
     *  UnionFind
     *  Runtime: 1376 ms, faster than 5.08% of Java online submissions for Surrounded Regions.
     * Memory Usage: 55.8 MB, less than 5.20% of Java online submissions for Surrounded Regions.
     */
    public void solve(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        int m = board.length;
        int n = board[0].length;
        UnionFind uf = new UnionFind(m*n+1);
        int dummyElement = m*n;
        int[][] directory = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(board[i][j] == 'O'){
                    if(i == 0 || i == m-1 || j == 0 || j == n-1){
                        uf.union(i*n+j, dummyElement);
                    }else{
                        for(int[] dir : directory){
                            int x = i + dir[0];
                            int y = j + dir[1];
                            if(x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O'){
                                uf.union(i*n+j, x*n+y);
                            }
                        }
                    }
                }
            }
        }
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(!uf.isConnected(i*n+j, dummyElement)){
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Surrounded Regions.
     * Memory Usage: 46.9 MB, less than 5.20% of Java online submissions for Surrounded Regions.
     */
    public void solve_dfs(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i < m; i++){
            if(board[i][0] == 'O'){
                dfs(board, i, 0);
            }
            if(board[i][n-1] == 'O'){
                dfs(board, i, n-1);
            }
        }
        for(int j=0; j < n; j++){
            if(board[0][j] == 'O'){
                dfs(board, 0, j);
            }
            if(board[m-1][j] == 'O'){
                dfs(board, m-1, j);
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j){
        if(i < 0 || i > board.length-1 || j < 0 || j > board[0].length-1){
            return;
        }
        if(board[i][j] == 'O'){
            board[i][j] = '*';
        }
        if(i > 1 && board[i-1][j] == 'O'){
            dfs(board, i-1, j);
        }
        if(j > 0 && board[i][j-1] == 'O'){
            dfs(board, i, j-1);
        }
        if(i < board.length - 1 && board[i+1][j] == 'O'){
            dfs(board, i+1, j);
        }
        if(j < board[0].length-1 && board[i][j+1] == 'O'){
            dfs(board, i, j+1);
        }
    }


    class UnionFind{
        int[] parent;
        int[] rank;
        UnionFind(int N){
            parent = new int[N];
            rank = new int[N];
            for(int i=0; i < N; i++){
                parent[i] = i;
            }
        }
        public int find(int p){
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
                parent[rootQ] = rootP;
            }else if(rank[rootP] > rank[rootQ]){
                parent[rootP] = rootQ;
            }else{
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }
        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }
    }

    public static void main(String[] args) {
        SurroundedRegion sr = new SurroundedRegion();
        char[][] arr =
                {
                        {'O','X','X','O','X'},
                        {'X','O','O','X','O'},
                        {'X','O','X','O','X'},
                        {'O','X','O','O','O'},
                        {'X','X','O','X','O'}
                };
        sr.solve(arr);
        System.out.println();
    }

}
