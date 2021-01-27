package com.piyush.psds.facebook.random;

import java.util.*;

public class AccountMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int id = 0;
        DSU dsu = new DSU(1001);
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for(int i=0; i < accounts.size(); i++) {
            String name = accounts.get(i).get(0);
            for(int j=1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                emailToName.put(email, name);
                if(!emailToId.containsKey(email)) {
                    emailToId.put(email, id++);
                }
                dsu.union(emailToId.get(accounts.get(i).get(1)), emailToId.get(accounts.get(i).get(j)));
            }
        }
        Map<Integer, List<String>> res = new HashMap<>();
        for(String email : emailToName.keySet()) {
            int index = dsu.find(emailToId.get(email));
            res.putIfAbsent(index, new ArrayList<>());
            res.get(index).add(email);
        }
        for(List<String> l : res.values()) {
            Collections.sort(l);
            l.add(0, emailToName.get(l.get(0)));
        }
        return new ArrayList<>(res.values());
    }

    class DSU {

        int[] parent;

        public DSU(int size) {
            parent = new int[size];
            for(int i=0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if(parent[i] == i) {
                return i;
            }
            return find(parent[i]);
        }

        public void union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);
            if(parentI != parentJ) {
                parent[parentJ] = parentI;
            }
        }

    }

}
