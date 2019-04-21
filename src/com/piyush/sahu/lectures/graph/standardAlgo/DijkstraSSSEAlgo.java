package com.piyush.sahu.lectures.graph.standardAlgo;

import com.piyush.sahu.lectures.graph.entities.DirectedEdge;
import com.piyush.sahu.lectures.graph.entities.WeightedDirectedGraph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Dijstra Algorithm:
 * put s in PQ/heap with key=0
 * while heap not empty
 * 	v <- EXTRACT MIN
 * 	for all v -> w
 * 		if v -> w is tense
 * 			relax v -> w
 * 			insert/update(v, dist(w))
 *
 * if negative cycle , it will not terminate
 * for No neg cycle: Each vertex is extracted once. (We extract vertices in increasing dist[v])
 * Complexity: O(E(logV))
 */
public class DijkstraSSSEAlgo {

    public static DijkstraPaths findShortestPath(WeightedDirectedGraph graph, int s){
        // Needed to be a queue
        PriorityQueue<Node> queue = new PriorityQueue<>(graph.getV(), new Node());
        double[] costs = new double[graph.getV()];
        boolean[] setteled = new boolean[graph.getV()];
        int[] paths = new int[graph.getV()];
        for(int i=0; i < graph.getV(); i++){
            costs[i] = Double.MAX_VALUE;
        }
        costs[s] = 0;

        queue.add(new Node(s, 0));
        while(!queue.isEmpty()){
            Node currNode = queue.remove();
            setteled[currNode.node] = true;
            System.out.println("Settled : " + currNode.node);
            for(DirectedEdge edge: graph.getAdjacencyList(currNode.node)){
                if(!setteled[edge.getV()] ) {
                    if (costs[edge.getV()] > costs[edge.getU()] + edge.getWeight()){
                        costs[edge.getV()] = costs[edge.getU()] + edge.getWeight();
                        queue.add(new Node(edge.getV(), costs[edge.getV()]));
                        paths[edge.getV()] = edge.getU();
                    }
                }
            }
        }
        return new DijkstraPaths(costs, paths);
    }

    static class DijkstraPaths{
        double[] distTo;
        int[] ancestor;

        public DijkstraPaths(double[] distTo, int[] ancestor) {
            this.distTo = distTo;
            this.ancestor = ancestor;
        }
    }


    static class Node implements Comparator<Node> {
        int node;
        double cost;

        public Node(){

        }

        public Node(int node, double cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node n1, Node n2) {
            if(n1.cost < n2.cost){
                return -1;
            }else if(n1.cost > n2.cost){
                return 1;
            }
            return 0;
        }
    }

    public static void printPathToV(DijkstraPaths paths, int s, int v){
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
        WeightedDirectedGraph graph = new WeightedDirectedGraph(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 0, 4);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 1, 8);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 5, 4);
        graph.addEdge(2, 8, 2);
        graph.addEdge(3, 2, 7);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 3, 9);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 2, 4);
        graph.addEdge(5, 3, 14);
        graph.addEdge(5, 4, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 5, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 0, 8);
        graph.addEdge(7, 1, 11);
        graph.addEdge(7, 6, 1);
        graph.addEdge(7, 8, 7);
        graph.addEdge(8, 2, 2);
        graph.addEdge(8, 6, 6);
        graph.addEdge(8, 7, 7);
        DijkstraPaths paths = findShortestPath(graph, 0);
        System.out.println(paths);
        for(int i=0; i < graph.getV(); i++) {
            printPathToV(paths, 0, i);
        }
    }

}
