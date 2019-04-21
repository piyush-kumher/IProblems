package com.piyush.sahu.lectures.graph.entities;

public class DirectedEdge {
    private final int u;
    private final int v;
    private final double weight;

    DirectedEdge(int u, int v, double weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public double getWeight() {
        return weight;
    }
}