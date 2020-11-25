package com.piyush.psds.aug_6_2020_streak.graph.union_find;

import java.util.Arrays;
import java.util.stream.IntStream;

public class UnionFind {

    private final int[] parent;
    private final int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        IntStream.range(0, n).forEach(i -> parent[i] = i);
    }

    public boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) {
            return true;
        }
        if (rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
        } else if (rank[parentB] > rank[parentA]) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
            rank[parentA] = rank[parentA] + 1;
        }
        return false;
    }

    public int find(int n) {
        if (parent[n] == n) {
            return n;
        }
        return find(parent[n]);
    }

    @Override
    public String toString() {
        return "\nParent: " + Arrays.toString(parent) + "\nRank: " + Arrays.toString(rank);
    }

}
