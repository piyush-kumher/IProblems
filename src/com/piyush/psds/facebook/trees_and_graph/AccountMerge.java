package com.piyush.psds.facebook.trees_and_graph;

import java.util.*;

public class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU d = new DSU(10001);
        Map<String, String> emailIdToName = new HashMap<>();
        Map<String, Integer> emailIdToID = new HashMap<>();
        int id = 0;
        for(List<String> account : accounts) {
            String name = account.get(0);
            for(int i=1; i < account.size(); i++) {
                String email = account.get(i);
                emailIdToName.put(email, name);
                if(!emailIdToID.containsKey(email)) {
                    emailIdToID.put(email, id++);
                }
                d.union(emailIdToID.get(account.get(1)), emailIdToID.get(email));
            }
        }
        Map<Integer, List<String>> ans = new HashMap<>();
        for(String email : emailIdToID.keySet()) {
            int index = d.find(emailIdToID.get(email));
            ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }
        for (List<String> component: ans.values()) {
            Collections.sort(component);
            component.add(0, emailIdToName.get(component.get(0)));
        }
        return new ArrayList(ans.values());
    }

    class DSU {
        int[] parent;
        public DSU(int size) {
            parent = new int[size];
            for(int i=0; i < size; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }

        public int find(int x) {
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
    }

}
