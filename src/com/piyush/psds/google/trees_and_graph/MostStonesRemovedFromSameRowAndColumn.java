package com.piyush.psds.google.trees_and_graph;

import java.util.*;

public class MostStonesRemovedFromSameRowAndColumn {


    public static void main(String[] args) {
        MostStonesRemovedFromSameRowAndColumn m = new MostStonesRemovedFromSameRowAndColumn();
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(m.removeStones(stones));
    }


    //The problem can be solved in 2 ways:
    // 1. DFS (complexity: n*n)
    // 2. UnionFind (complexity: n * log n)
    // lets understand the problem: This problem is not about moving in certain direction.
    // It's about removing a stone from anywhere if it shares either the row or column with another stone.
    // Lets define a group of stones which are connected through rows and columns,
    // if we start removing stones from a group. at the end there would remain a stone which can not be removed
    // because we have removed others and it does not share any row and column with other stones.
    // Hence each group would a single non visited stone. we can say the answer would be len(stones) - no_of_groups


    // 1. Approach: DFS : 58%
    public int removeStones(int[][] stones) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] stone : stones) {
            graph.putIfAbsent(stone[0], new ArrayList<>());
            graph.putIfAbsent(~stone[1], new ArrayList<>()); // this is to differntiate between row and column: We can as well add it up with 10000 (as the limit of stones is 10000)
            graph.get(stone[0]).add(stone);
            graph.get(~stone[1]).add(stone);
        }

        int groups = 0;
        Map<Integer, Map<Integer, Boolean>> visited = new HashMap<>();
        Stack<int[]> stk = new Stack<>();
        for(int[] stone : stones) {
            if(!visited.containsKey(stone[0]) || !visited.get(stone[0]).containsKey(stone[1])) {
                groups++;
                stk.push(stone);
                visited.putIfAbsent(stone[0], new HashMap<>());
                visited.get(stone[0]).put(stone[1], true);
                while(!stk.isEmpty()) {
                    int[] curStone = stk.pop();
                    for(int[] nextStone : graph.get(curStone[0])) {
                        if(!visited.containsKey(nextStone[0]) || !visited.get(nextStone[0]).containsKey(nextStone[1])) {
                            visited.putIfAbsent(nextStone[0], new HashMap<>());
                            visited.get(nextStone[0]).put(nextStone[1], true);
                            stk.push(nextStone);
                        }
                    }
                    for(int[] nextStone : graph.get(~curStone[1])) {
                        if(!visited.containsKey(nextStone[0]) || !visited.get(nextStone[0]).containsKey(nextStone[1])) {
                            visited.putIfAbsent(nextStone[0], new HashMap<>());
                            visited.get(nextStone[0]).put(nextStone[1], true);
                            stk.push(nextStone);
                        }
                    }
                }
            }
        }
        return stones.length - groups;
    }


    // dfs #2 // 28%
    public int removeStones2(int[][] stones) {
        if(stones == null || stones.length <= 1){
            return 0;
        }
        int N = stones.length;
        boolean[] visited = new boolean[N];
        int noOfComponents = 0;
        for(int i=0; i < N; i++){
            if(!visited[i]){
                dfs(i, stones, visited);
                noOfComponents++;
            }
        }
        return N - noOfComponents;
    }

    public void dfs(int stone, int[][] stones, boolean[] visited){
        visited[stone] = true;
        for(int i=0; i < stones.length; i++){
            if(visited[i]){
                continue;
            }
            if(stones[stone][0] == stones[i][0] || stones[stone][1] == stones[i][1]){
                dfs(i, stones, visited);
            }
        }
    }


    // dfs #3  // 52%
    public int removeStones3(int[][] stones) {
        int N = stones.length;

        // graph[i][0] = the length of the 'list' graph[i][1:]
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j)
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    graph[i][++graph[i][0]] = j;
                    graph[j][++graph[j][0]] = i;
                }

        int ans = 0;
        boolean[] seen = new boolean[N];
        for (int i = 0; i < N; ++i) if (!seen[i]) {
            Stack<Integer> stack = new Stack();
            stack.push(i);
            seen[i] = true;
            ans--;
            while (!stack.isEmpty()) {
                int node = stack.pop();
                ans++;
                for (int k = 1; k <= graph[node][0]; ++k) {
                    int nei = graph[node][k];
                    if (!seen[nei]) {
                        stack.push(nei);
                        seen[nei] = true;
                    }
                }
            }
        }

        return ans;
    }



}
