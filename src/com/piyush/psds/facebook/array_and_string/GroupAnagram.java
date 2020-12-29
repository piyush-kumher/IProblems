package com.piyush.psds.facebook.array_and_string;

import java.util.*;

public class GroupAnagram {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] arr;
        for(String str : strs) {
            arr = new int[26];
            for(char ch : str.toCharArray()) {
                arr[ch-'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < 26; i++) {
                sb.append(arr[i]).append('#');
            }
            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams_1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            map.putIfAbsent(sortedStr, new ArrayList<>());
            map.get(sortedStr).add(str);
        }
        return new ArrayList<>(map.values());
    }

}
