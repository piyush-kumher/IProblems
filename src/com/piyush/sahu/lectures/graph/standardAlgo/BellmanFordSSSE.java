package com.piyush.sahu.lectures.graph.standardAlgo;

import com.piyush.sahu.lectures.graph.entities.DirectedEdge;
import com.piyush.sahu.lectures.graph.entities.WeightedDirectedGraph;

/**
 * Single source shortest path: Path from a vetex S to very other vertex in the graph. The shortest
 * path can be represented by a tree.
 *
 * Input = directed graph G = (V, E) lengths  l: E --> R Given: Vertices S + T path S = V0 --> V1
 * --> v2 --> .. --> Vk = T A sub-path of shortest path in also a shortest path in itself. If there
 * is an negative cycle, then shortest path doesn't exists. (Negative cycle means: sum of length of
 * the cycle is negative)
 *
 *
 *
 * Bellman ford: Initialization: dist(s) <- 0 for all v != s: dist(v) = INF for all v:
 * predecessor(v) <- NULL
 *
 * # u->v is tense (relaxing step) if dist(u) + l(u -> v) < dist(v): dist(v) = dist(u) + l(u -> v)
 * predecessor(v) <- u
 *
 * for i = range(|V|-1): Relax all the edges
 *
 * Complexity: O(VE) Bellman ford works for negative edges but fails for negative weight cycle.
 * Bellman ford can be used to detect if there is negative cycle. Try relaxing it one more than if
 * edges are relaxing than there is negative weight cycle.
 */

public class BellmanFordSSSE {

    static class BellmanFordPaths{
        double[] distTo;
        int[] ancestor;

        public BellmanFordPaths(double[] distTo, int[] ancestor) {
            this.distTo = distTo;
            this.ancestor = ancestor;
        }
    }

    public static BellmanFordPaths findShortestPath(WeightedDirectedGraph graph, int s) {
        double[] distTo = new double[graph.getV()];
        int[] ancestor = new int[graph.getV()];
        for (int i = 0; i < graph.getV(); i++) {
            distTo[i] = Double.MAX_VALUE;
        }
        distTo[s] = 0;
        for (int i = 0; i < graph.getV() - 1; i++) {
            for (DirectedEdge edge : graph.edges()) {
                if (distTo[edge.getV()] > distTo[edge.getU()] + edge.getWeight()) {
                    distTo[edge.getV()] = distTo[edge.getU()] + edge.getWeight();
                    ancestor[edge.getV()] = edge.getU();
                }
            }
        }
        for (DirectedEdge edge : graph.edges()) {
            if (distTo[edge.getV()] != Double.MAX_VALUE &&
                    distTo[edge.getV()] > distTo[edge.getU()] + edge.getWeight()) {
                System.out.println("Negative cycle exists");
                throw new RuntimeException("Negative cycle exists");
            }
        }
        return new BellmanFordPaths(distTo, ancestor);
    }

    public static void printPathToV(BellmanFordPaths paths, int s, int v){
        System.out.println("Distance is: " + paths.distTo[v]);
        int element = v;
        if(paths.distTo[v] == Double.MAX_VALUE){
            throw new RuntimeException("Path does not exists");
        }
        while(element != s){
            System.out.print(element + "-->");
            element = paths.ancestor[element];
        }
        System.out.println(element);
    }

    public static void main(String[] args) {
        WeightedDirectedGraph graph = new WeightedDirectedGraph(5);
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);
        BellmanFordPaths paths = findShortestPath(graph, 0);
        System.out.println(paths);
        printPathToV(paths, 0, 1);
        printPathToV(paths, 0, 2);
        printPathToV(paths, 0, 3);
        printPathToV(paths, 0, 4);

        //Negative cycle
//        WeightedDirectedGraph negativeCycleGraph = new WeightedDirectedGraph(5);
//        negativeCycleGraph.addEdge(0, 1, -1);
//        negativeCycleGraph.addEdge(0, 2, 4);
//        negativeCycleGraph.addEdge(1, 2, 3);
//        negativeCycleGraph.addEdge(1, 3, 2);
//        negativeCycleGraph.addEdge(1, 4, 2);
//        negativeCycleGraph.addEdge(3, 1, 1);
//        negativeCycleGraph.addEdge(4, 3, -4);
//        BellmanFordPaths newPaths = findShortestPath(negativeCycleGraph, 0);
//        System.out.println(newPaths);

        WeightedDirectedGraph noPath = new WeightedDirectedGraph(5);
        noPath.addEdge(0, 1, -1);
        noPath.addEdge(0, 2, 4);
        noPath.addEdge(1, 2, 3);
        noPath.addEdge(1, 3, 2);
        noPath.addEdge(4, 1, 2);
        noPath.addEdge(3, 1, 1);
        noPath.addEdge(4, 3, -3);
        BellmanFordPaths noPaths = findShortestPath(noPath, 0);
        printPathToV(noPaths, 0, 4);
    }

}
