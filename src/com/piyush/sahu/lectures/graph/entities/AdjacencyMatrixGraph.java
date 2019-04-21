package com.piyush.sahu.lectures.graph.entities;

public class AdjacencyMatrixGraph {

    private int numberOfVertices;
    private int[][] adjacencyMatrix;

    public AdjacencyMatrixGraph(int numberOfVertices){
        this.numberOfVertices = numberOfVertices;
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    }

    public void addEdge(int node1, int node2){
        adjacencyMatrix[node1][node2] = 1;
    }

    public int getNumberOfVertices(){
        return numberOfVertices;
    }

    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i=0; i < numberOfVertices; i++){
            s.append(i).append(" is connected to:");
            for(int j=0; j < numberOfVertices; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    s.append(" ").append(j).append(",");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

}
