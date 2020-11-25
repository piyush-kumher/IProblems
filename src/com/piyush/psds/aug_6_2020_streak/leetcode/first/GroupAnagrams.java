package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/solution/
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams g = new GroupAnagrams();
        System.out.println(g.groupAnagrams(new String[]{"Sexy"}));
    }

    public List<List<String>> groupAnagrams_1(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> mem = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedString = new String(arr);
            mem.putIfAbsent(sortedString, new ArrayList<>());
            mem.get(sortedString).add(str);
        }
        return new ArrayList<>(mem.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> mem = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : count) {
                sb.append(i).append('#');
            }
            String key = sb.toString();
            mem.putIfAbsent(key, new ArrayList<>());
            mem.get(key).add(str);
        }
        return new ArrayList<>(mem.values());
    }

}
