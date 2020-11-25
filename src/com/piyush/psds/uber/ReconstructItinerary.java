package com.piyush.psds.uber;

import java.util.*;

public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String source = ticket.get(0);
            String dest = ticket.get(1);
            graph.putIfAbsent(source, new PriorityQueue<>());
            graph.get(source).add(dest);
        }
        LinkedList<String> result = new LinkedList<>();
        dfs("JFK", graph, result);
        return result;
    }

    private void dfs(String source, Map<String, PriorityQueue<String>> graph, LinkedList<String> result) {
        PriorityQueue<String> adj = graph.get(source);
        while (adj != null && !adj.isEmpty()) {
            dfs(adj.poll(), graph, result);
        }
        result.addFirst(source);
    }
}
