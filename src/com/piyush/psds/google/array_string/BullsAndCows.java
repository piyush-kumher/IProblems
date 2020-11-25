package com.piyush.psds.google.array_string;

// https://leetcode.com/problems/bulls-and-cows/submissions/
public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int[] sm = new int[10];
        int bulls = 0;
        int cows = 0;
        for(int i=0; i < secret.length(); i++) {
            sm[secret.charAt(i)-'0']++;
        }
        for(int i=0; i < guess.length(); i++) {
            if(i < secret.length() && secret.charAt(i) == guess.charAt(i)) {
                sm[secret.charAt(i)-'0']--;
                bulls++;
                if(sm[secret.charAt(i) - '0'] < 0) {
                    sm[secret.charAt(i)-'0']++;
                    cows--;
                }
            } else if(sm[guess.charAt(i)-'0'] > 0) {
                sm[guess.charAt(i)-'0']--;
                cows++;
            }
        }
        return (bulls + "A" + cows + "B");
    }


}
