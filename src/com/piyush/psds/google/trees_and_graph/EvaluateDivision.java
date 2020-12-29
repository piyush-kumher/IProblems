package com.piyush.psds.google.trees_and_graph;

import java.util.*;

public class EvaluateDivision {

    // dfs
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Graph g = new Graph();
        for(int i=0; i < equations.size(); i++) {
            g.addBiEdge(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] answers = new double[queries.size()];
        Set<String> visited;
        for(int i=0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            visited = new HashSet<>();
            double ans = dfs(g, from, to, visited);
            answers[i] = ans;
        }
        return answers;
    }

    private double dfs(Graph g, String from, String to, Set<String> visited) {
        if(g.graph.containsKey(from) && from.equals(to)) {
            return 1.0d;
        }
        visited.add(from);
        if(g.graph.containsKey(from)) {
            //System.out.println("from:" + from + ", to:" + to);
            for(Node node : g.graph.get(from)) {
                if(!visited.contains(node.to)) {
                    //System.out.println("node.to:" + node.to + ", node.w:" + node.w);
                    double ans = dfs(g, node.to, to, visited);
                    //System.out.println("node.to:" + node.to + ", node.w:" + node.w + ", ans: " + ans);
                    if(ans != -1.0d) {
                        return node.w * ans;
                    }
                }
            }
        }
        return -1.0d;
    }

    class Graph {
        Map<String, List<Node>> graph = new HashMap<>();

        public void addBiEdge(String from, String to, double val) {
            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());
            graph.get(from).add(new Node(to, val));
            graph.get(to).add(new Node(from, 1.0/val));
        }
    }

    class Node {
        String to;
        double w;
        public Node(String to, double w) {
            this.to = to;
            this.w = w;
        }
    }
}
