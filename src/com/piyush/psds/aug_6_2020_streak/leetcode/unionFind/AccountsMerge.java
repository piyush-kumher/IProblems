package com.piyush.psds.aug_6_2020_streak.leetcode.unionFind;

import java.util.*;

/**
 * https://leetcode.com/problems/accounts-merge/submissions/
 */
public class AccountsMerge {

    public static void main(String[] args) {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        );
        AccountsMerge t = new AccountsMerge();
        List<List<String>> res = t.accountsMerge(t.accountsMerge(list));
        System.out.println(res);
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if (accounts == null || accounts.size() <= 1) {
            return accounts;
        }
        int records = accounts.size();
        int[] linkedTo = new int[records];
        for (int i = 0; i < records; i++) {
            linkedTo[i] = i;
        }
        int[] rank = new int[records];
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Integer> emailtoId = new HashMap<>();
        int id = 0;
        DSU dsu = new DSU();
        for (int i = 0; i < records; i++) {
            List<String> emails = accounts.get(i);
            String name = emails.get(0);
            for (int j = 1; j < emails.size(); j++) {
                String email = emails.get(j);
                emailToName.put(email, name);
                if (!emailtoId.containsKey(email)) {
                    emailtoId.put(email, id++);
                }
                dsu.union(emailtoId.get(emails.get(1)), emailtoId.get(email));
            }
        }
        Map<Integer, List<String>> res = new HashMap<>();
        for (String email : emailToName.keySet()) {
            int index = dsu.find(emailtoId.get(email));
            res.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
        }
        for (List<String> component : res.values()) {
            Collections.sort(component);
            component.add(0, emailToName.get(component.get(0)));
        }
        return new ArrayList(res.values());
    }

    class DSU {
        int[] parent;

        public DSU() {
            parent = new int[10001];
            for (int i = 0; i <= 10000; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

}
