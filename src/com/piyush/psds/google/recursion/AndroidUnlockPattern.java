package com.piyush.psds.google.recursion;

public class AndroidUnlockPattern {
    // Android Pattern Unlock
    // [1,2,3]
    // [4,5,6]
    // [7,8,9]
    // 1. We can move to any adjacent block
    // 2. We can take knight move.. ()ex: 1 -> 6, 1 -> 8, 3 ->8 3 -> 4, 4 -> 3 etc ..
    // 3. A person can not jump a cell unless that cell in already counted in the path. ex: 1 -> 7 is not allowed, unless 4 was already covered.
    // In summary, move can be anywhere except the 3rd condition. We need to handle third usecase.
    // As we can seen we have 2 symmetric group: 1,3,7,9 and 2,4,8,6. We can calculate for one from each group and multiply by 4.
    public int numberOfPatterns(int m, int n) {
        // Figure out the skip part : 3rd condition
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;

        int sum = 0;
        boolean[] visited = new boolean[10];
        for(int len=m; len <= n; len++) {
            sum += dfs(visited, skip, 1, len-1) * 4; // multiple by 4 for 1,3,7,9
            sum += dfs(visited, skip, 2, len-1) * 4; // multiple by 4 for 2,4,8,6
            sum += dfs(visited, skip, 5, len-1);
        }
        return sum;
    }

    private int dfs(boolean[] visited, int[][] skip, int curr, int remain) {
        if(remain < 0 ) {
            return remain;
        }
        if(remain == 0) {
            return 1;
        }
        visited[curr] = true;
        int result = 0;
        for(int i=1; i <= 9; i++) {
            if(!visited[i] && (skip[curr][i] == 0 || visited[skip[curr][i]])) {
                result += dfs(visited, skip, i, remain-1);
            }
        }
        visited[curr] = false;
        return result;
    }
}
