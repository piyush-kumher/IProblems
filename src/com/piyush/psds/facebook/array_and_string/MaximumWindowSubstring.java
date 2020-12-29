package com.piyush.psds.facebook.array_and_string;

import java.util.*;

public class MaximumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        // Getting the character count of t
        Map<Character, Integer> tchCount = new HashMap<>();
        for(char ch : t.toCharArray()) {
            tchCount.put(ch, tchCount.getOrDefault(ch, 0) + 1);
        }

        int required = tchCount.size();
        int formed = 0;
        int l = 0;
        int r = 0;
        int length = -1;
        int start = 0;
        int end = 0;
        Map<Character, Integer> schCount = new HashMap<>();

        while(r < s.length()) {
            char ch = s.charAt(r);
            schCount.put(ch, schCount.getOrDefault(ch, 0)+1);

            if(tchCount.containsKey(ch) && schCount.get(ch).intValue() == tchCount.get(ch).intValue()) {
                formed++;
            }
            while(formed == required && l <= r) {
                if(length == -1 || r-l+1 < length) {
                    length = r-l+1;
                    start = l;
                    end = r;
                }
                char removedCh = s.charAt(l);
                schCount.put(removedCh, schCount.get(removedCh) - 1);
                if(tchCount.containsKey(removedCh) && schCount.get(removedCh).intValue() < tchCount.get(removedCh).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return length == -1 ? "" : s.substring(start, end+1);
    }




    public String minWindow_1(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        // Getting the character count of t
        Map<Character, Integer> tchCount = new HashMap<>();
        for(char ch : t.toCharArray()) {
            tchCount.put(ch, tchCount.getOrDefault(ch, 0) + 1);
        }

        List<CharPair> filteredS = new ArrayList<>();
        for(int i=0; i < s.length(); i++) {
            if(tchCount.containsKey(s.charAt(i))) {
                filteredS.add(new CharPair(s.charAt(i), i));
            }
        }

        int required = tchCount.size();
        int formed = 0;
        int l = 0;
        int r = 0;
        int length = -1; int start = 0; int end = 0;
        Map<Character, Integer> schCount = new HashMap<>();

        while(r < filteredS.size()) {
            char ch = filteredS.get(r).ch;
            int index = filteredS.get(r).index;
            schCount.put(ch, schCount.getOrDefault(ch, 0)+1);

            if(schCount.get(ch).intValue() == tchCount.get(ch).intValue()) {
                formed++;
            }
            while(formed == required && l <= r) {
                char removedCh = filteredS.get(l).ch;
                int removedIndex = filteredS.get(l).index;
                if(length == -1 || index-removedIndex+1 < length) {
                    length = index-removedIndex+1;
                    start = removedIndex;
                    end = index;
                }
                schCount.put(removedCh, schCount.get(removedCh) - 1);
                if(schCount.get(removedCh).intValue() < tchCount.get(removedCh).intValue()) {
                    formed--;
                }
                l++;
            }
            r++;
        }
        return length == -1 ? "" : s.substring(start, end+1);
    }

    class CharPair {
        char ch;
        int index;
        public CharPair(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }

}
