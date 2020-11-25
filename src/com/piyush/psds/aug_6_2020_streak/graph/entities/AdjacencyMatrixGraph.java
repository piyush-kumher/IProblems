package com.piyush.psds.aug_6_2020_streak.graph.entities;

public class AdjacencyMatrixGraph {

    private final int len;
    private final int[][] matrix;

    public AdjacencyMatrixGraph(int len) {
        this.len = len;
        this.matrix = new int[len][len];
    }

    public void addEdge(int src, int dest) {
        matrix[src][dest] = 1;
    }

    public int getNumberOfVertices() {
        return len;
    }

    public int[][] getAdjacencyMatrix() {
        return matrix;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < len; i++) {
            s.append(i).append(" is connected to:");
            for (int j = 0; j < len; j++) {
                if (matrix[i][j] == 1) {
                    s.append(" ").append(j).append(",");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}
