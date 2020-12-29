package com.piyush.psds.facebook.dp;

import java.util.*;

public class WordBreak {

    public boolean wordBreak_1(String s, List<String> wordDict) {
        Set<String> notPossible = new HashSet<>();
        return recurse(s, wordDict, notPossible);
    }

    private boolean recurse(String s, List<String> wordDict, Set<String> notPossible) {
        if(s.length() == 0) {
            return true;
        }
        for(String dict : wordDict) {
            if(s.startsWith(dict)) {
                String ss = s.substring(dict.length());
                if(!notPossible.contains(ss)) {
                    if(recurse(ss, wordDict, notPossible)) {
                        return true;
                    } else {
                        notPossible.add(ss);
                    }
                }
            }
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] mem = new boolean[s.length()+1];
        mem[0] = true;
        for(int i=1; i <= s.length(); i++) {
            for(int j=0; j < i; j++) {
                if(mem[j] && dict.contains(s.substring(j, i))) {
                    mem[i] = true;
                    break;
                }
            }
        }
        return mem[s.length()];
    }

}
