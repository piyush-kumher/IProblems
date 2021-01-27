package com.piyush.psds.google.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TheMostSimilarPath {


    int[][] dp;
    int[][] nextChoiceForMin;
    List<List<Integer>> adjMatrix;
    String[] names;
    String[] targetPath;

    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        dp = new int[names.length][targetPath.length];
        nextChoiceForMin = new int[names.length][targetPath.length];
        this.names = names;
        this.targetPath = targetPath;
        this.adjMatrix = new ArrayList<>();

        for (int i = 0; i < names.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            adjMatrix.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            adjMatrix.get(road[0]).add(road[1]);
            adjMatrix.get(road[1]).add(road[0]);
        }

        int start = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < names.length; i++) {
            int ed = dfs(i, 0);
            if (min > ed) {
                min = ed;
                start = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        while (ans.size() < targetPath.length) {
            ans.add(start);
            start = nextChoiceForMin[start][ans.size() - 1];
        }
        return ans;

    }

    private int dfs(int nameId, int currentPathId) {
        if (dp[nameId][currentPathId] != -1) {
            return dp[nameId][currentPathId];
        }
        int ed = names[nameId].equals(targetPath[currentPathId]) ? 0 : 1;
        if (currentPathId == targetPath.length - 1) {
            return ed;
        }
        int min = Integer.MAX_VALUE;
        for (int next : adjMatrix.get(nameId)) {
            int nextEd = dfs(next, currentPathId + 1);
            if (nextEd < min) {
                min = nextEd;
                nextChoiceForMin[nameId][currentPathId] = next;
            }
        }
        ed += min;
        dp[nameId][currentPathId] = ed;
        return ed;
    }

}
