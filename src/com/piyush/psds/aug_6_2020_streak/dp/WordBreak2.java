package com.piyush.psds.aug_6_2020_streak.dp;

import java.util.*;

public class WordBreak2 {

    public static void main(String[] args) {
        WordBreak2 w2 = new WordBreak2();
        List<String> dict = Arrays.asList(new String[] {"cat","cats","and","sand","dog"});
        String s = "catsanddog";
        System.out.println(w2.wordBreak(s, dict));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<>());
    }

    private List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> mem) {
        if(mem.containsKey(s)) {
            return mem.get(s);
        }

        List<String> result = new ArrayList<>();
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.length() == 0) {
                    result.add(word);
                } else {
                    for(String sub : backtrack(next, wordDict, mem)) {
                        result.add(word + " " + sub);
                    }
                }
            }
        }
        mem.put(s, result);
        return result;
    }

    public List<String> wordBreak_2(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return perform(s, 0, map, wordDict);
    }

    private List<String> perform(String s, int start, Map<String, List<String>> map, List<String> wordDict) {
        if(map.containsKey(s)) {
            return map.get(s);
        }
        List<String> result = new ArrayList<>();
        if(s.length() == 0) {
            result.add("");
            return result;
        }
        for(String word: wordDict) {
            if(s.startsWith(word)) {
                String sub = s.substring(word.length());
                List<String> suffixes = perform(sub, word.length(), map, wordDict);
                for(String suffix : suffixes) {
                    result.add(word + (suffix.length() != 0 ? " " : "") +  suffix);
                }
            }
        }
        map.put(s, result);
        return result;
    }

    public List<String> wordBreak_1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<String>> map = new HashMap<>();
        return find(s, 0, map, set);
    }

    private List<String> find(String s, int start, Map<Integer, List<String>> map, Set<String> set) {
        if(map.containsKey(start)) {
            return map.get(start);
        }
        List<String> result = new ArrayList<>();
        if(start >= s.length()) {
            result.add("");
        }
        for(int i=start+1; i <= s.length(); i++) {
            String w = s.substring(start, i);
            if(set.contains(w)) {
                List<String> suffixes = find(s, i, map, set);
                for(String suffix : suffixes) {
                    result.add(w + (suffix.length() != 0 ? " " : "") +  suffix);
                }
            }
        }
        map.put(start, result);
        return result;
    }

}
