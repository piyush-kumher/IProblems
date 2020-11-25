package com.piyush.psds.graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<String>> adjList = new HashMap<>();
        Map<String, List<Double>> weightMatrix = new HashMap<>();
        for(int i=0; i < equations.length; i++){
            String from = equations[i][0];
            String to = equations[i][1];
            if(!adjList.containsKey(from)){
                adjList.put(from, new ArrayList<>());
                weightMatrix.put(from, new ArrayList<>());
            }
            if(!adjList.containsKey(to)){
                adjList.put(to, new ArrayList<>());
                weightMatrix.put(to, new ArrayList<>());
            }
            adjList.get(from).add(to);
            adjList.get(to).add(from);
            weightMatrix.get(from).add(values[i]);
            weightMatrix.get(to).add(1/values[i]);
        }
        double[] result = new double[queries.length];
        for(int i=0; i < queries.length; i++){
            result[i] = dfs(queries[i][0], queries[i][1], adjList, weightMatrix, new HashSet<String>(), 1.0);
            if(result[i] == 0.0){
                result[i] = -1.0;
            }
        }
        return result;
    }

    private double dfs(String node1, String node2, Map<String, List<String>> adjList,
                    Map<String, List<Double>> weightMatrix, HashSet<String> set, double value) {
        if(set.contains(node1)){
            return 0.0;
        }
        if(!adjList.containsKey(node1)){
            return 0.0;
        }
        if(node1.equals(node2)){
            return value;
        }
        set.add(node1);
        double tmp = 0.0;
        for(int i=0; i < adjList.get(node1).size(); i++){
            String nextNode = adjList.get(node1).get(i);
            tmp = dfs(nextNode, node2, adjList, weightMatrix, set,value * weightMatrix.get(node1).get(i));
            if(tmp != 0.0){
                break;
            }
        }
        set.remove(node1);
        return tmp;
    }

    public static void main(String[] args) {
        EvaluateDivision ed = new EvaluateDivision();
        String[][] equations = {{"a","b"},{"b","c"}};
        double[] values= {2.0,3.0};
        String[][] queries = { {"a","c"},{"b","c"},{"a","e"},{"a","a"},{"x","x"} };
        ed.calcEquation(equations, values, queries);
    }

}
