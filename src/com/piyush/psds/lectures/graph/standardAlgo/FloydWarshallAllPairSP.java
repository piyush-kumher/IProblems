package com.piyush.psds.lectures.graph.standardAlgo;

/**
 * Time complexity: O(V*V*V)
 * Space Complexity: O(V*V)
 * It assumes no negative cycle, if any exists this can find them.
 * If the node distance from itself becomes negative that's where we will find negative cycles.
 */
public class FloydWarshallAllPairSP {

    private static int INF = Integer.MAX_VALUE;

    public static int[][] calculate(int[][] graph){
        int V = graph.length;
        int[][] dist = new int[V][V];

        for(int i=0; i < V; i++){
            for(int j=0; j < V; j++){
                dist[i][j] = graph[i][j];
            }
        }

        for(int k=0; k < V; k++ ){
            for(int i=0; i < V; i++){
                for (int j=0; j < V; j++){
                    if(dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE && dist[i][j] > dist[i][k] + dist[k][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 3, INF, 7},
                {8, 0, 2, INF},
                {5, INF, 0, 1},
                {2, INF, INF, 0}
        };
        int[][] dist = calculate(graph);
        for(int i=0; i < dist.length; i++){
            for(int j=0; j < dist.length; j++){
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
