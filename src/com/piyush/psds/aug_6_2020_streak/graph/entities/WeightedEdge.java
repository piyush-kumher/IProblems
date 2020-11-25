package com.piyush.psds.aug_6_2020_streak.graph.entities;

public class WeightedEdge {
    public final int from;
    public final int to;
    public final int weight;

    public WeightedEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}