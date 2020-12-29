package com.piyush.psds.google.others;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {

    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> j = new HashSet<>();
        for(char c : jewels.toCharArray()) {
            j.add(c);
        }
        int ans = 0;
        for(char c : stones.toCharArray()) {
            if(j.contains(c)) {
                ans++;
            }
        }
        return ans;
    }

}
