package com.piyush.psds.aug_6_2020_streak.dp;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, word1.length(), word2.length(), new int[word1.length()+1][word2.length()+1]);
    }

    private int minDistance(String word1, String word2, int n, int m, int[][] mem) {
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        if(mem[n][m] != 0) {
            return mem[n][m];
        }
        int minValue = 0;
        if(word1.charAt(n-1) == word2.charAt(m-1)) {
            minValue = minDistance(word1, word2, n-1, m-1, mem);
        } else {
            int insert = minDistance(word1, word2, n, m-1, mem);
            int delete = minDistance(word1, word2, n-1, m, mem);
            int replace = minDistance(word1, word2, n-1, m-1, mem);
            minValue = 1 + Math.min(insert, Math.min(delete, replace));
        }
        mem[n][m] = minValue;
        return minValue;
    }


    public int minDistance_dp_without_recursion(String word1, String word2) {
        int[][] mem = new int[word2.length()+1][word1.length()+1];
        int count = 1;
        for(int i=1; i < mem.length; i++) {
            mem[i][0] = count++;
        }
        count = 1;
        for(int i=1; i < mem[0].length; i++) {
            mem[0][i] = count++;
        }
        for(int i=1; i <= word2.length(); i++) {
            for(int j=1; j <= word1.length(); j++) {
                int left = mem[i][j-1] + 1;
                int up = mem[i-1][j] + 1;
                int mid = mem[i-1][j-1];
                int val = Math.min(mem[i-1][j-1], Math.min(mem[i-1][j], mem[i][j-1]));
                if(word2.charAt(i-1) != word1.charAt(j-1)) {
                    mid = mid + 1;
                }
                mem[i][j] = Math.min(left, Math.min(up, mid));
            }
        }
        return mem[word2.length()][word1.length()];
    }


}
