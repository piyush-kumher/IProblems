package com.piyush.psds.aug_6_2020_streak.leetcode.unionFind;

import java.util.*;

public class EvaluateDivision {

    public static void main(String[] args) {
        EvaluateDivision ed = new EvaluateDivision();


//        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("bc", "cd"));
//        double[] values = {1.5, 2.5, 5.0};
//        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("c", "b"),
//                Arrays.asList("bc", "cd"), Arrays.asList("cd", "bc"));


        List<List<String>> equations = Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b","a"),
                Arrays.asList("a","e"), Arrays.asList("a","a"), Arrays.asList("x","x"));

        System.out.println(Arrays.toString(ed.calcEquation(equations, values, queries)));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if(equations == null || equations.size() < 1) {
            return new double[0];
        }
        Graph g = new Graph();
        for(int i=0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            String from = equation.get(0);
            String to = equation.get(1);
            g.addEdge(from, to, values[i]);
            g.addEdge(to,from, 1.0/values[i]);
        }
        Set<String> visited = null;
        double[] ans = new double[queries.size()];
        for(int i=0; i < queries.size(); i++) {
            visited = new HashSet<>();
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            double queryAns = dfs(g, visited, from, to);
            ans[i] = queryAns;
        }
        for(int i=0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
        return ans;
    }

    private double dfs(Graph g, Set<String> visited, String from, String to) {
        visited.add(from);
        if(!g.map.containsKey(from) || !g.map.containsKey(to)) {
            return -1.0;
        }
        if(from.equals(to)) {
            return 1.0;
        }
        for(WeightNode n : g.map.get(from)) {
            if(!visited.contains(n.to)) {
                double dist = dfs(g, visited, n.to, to);
                if(dist != -1.0) {
                    return n.weight * dist;
                }
            }
        }
        return -1.0;
    }

    class Graph {
        Map<String, List<WeightNode>> map;
        public Graph() {
            this.map = new HashMap<>();
        }
        public void addEdge(String from, String to, double weight) {
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(new WeightNode(to, weight));
        }
    }

    class WeightNode {
        String to;
        double weight;
        public WeightNode(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
