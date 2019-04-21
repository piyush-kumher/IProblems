package com.piyush.sahu.graph.leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ItineraryArrangement {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(int i =0; i < tickets.length; i++){
            if(!map.containsKey(tickets[i][0])){
                map.put(tickets[i][0], new PriorityQueue<>());
            }
            map.get(tickets[i][0]).add(tickets[i][1]);
        }
        List<String> list = new ArrayList<>();
        dfs("JFK", map, list);
        return list;
    }

    private void dfs(String node, Map<String, PriorityQueue<String>> map, List<String> list){
        list.add(node);
        System.out.println(node);
        String nextNode = (map.get(node) == null || map.get(node).size() == 0)  ? null : map.get(node).remove();
        if(nextNode != null){
            dfs(nextNode, map, list);
        }
    }



    public List<String> findItineraryAnother(String[][] tickets) {
        Map<String, PriorityQueue<String>> flights = new HashMap<>();
        LinkedList<String> path = new LinkedList<>();
        for (String[] ticket : tickets) {
            flights.putIfAbsent(ticket[0], new PriorityQueue<>());
            flights.get(ticket[0]).add(ticket[1]);
        }
        dfs("JFK", flights, path);
        return path;
    }

    public void dfs(String departure, Map<String, PriorityQueue<String>> flights, LinkedList<String> path) {
        System.out.println(departure);
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll(), flights, path);
        path.addFirst(departure);
    }

    public static void main(String[] args) {
        ItineraryArrangement ia = new ItineraryArrangement();
        //String[][] str = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        String[][] str = {{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        ia.findItineraryAnother(str);
    }
}
