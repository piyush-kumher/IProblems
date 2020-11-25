package com.piyush.psds.lectures.graph.entities;

import java.util.LinkedList;

public class AdjacencyListGraph<T> {

    private int vertexLength;
    private LinkedList<T>[] adjacencyList;

    public AdjacencyListGraph(int noOfVertexes){
        this.vertexLength = noOfVertexes;
        this.adjacencyList = new LinkedList[noOfVertexes];
        for(int i=0; i < noOfVertexes; i++){
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public int getNoOfVertices(){
        return vertexLength;
    }

    public LinkedList<T>[] getAdjacencyList(){
        return adjacencyList;
    }

    public void addEdge(int i, T node){
        adjacencyList[i].add(node);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i=0; i < vertexLength; i++){
            s.append(i).append(" is connected to:");
            for (T t : adjacencyList[i]) {
                s.append(" ").append(t).append(",");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
