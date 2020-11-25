package com.piyush.psds.aug_6_2020_streak.dp;

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        boolean[][] mem = new boolean[s1.length()+1][s2.length()+1];
        mem[0][0] = true;
        for(int i=1; i <= s1.length(); i++) {
            if(s1.charAt(i-1) == s3.charAt(i-1)) {
                mem[i][0] = true;
            } else {
                break;
            }
        }
        for(int i=1; i <= s2.length(); i++) {
            if(s2.charAt(i-1) == s3.charAt(i-1)) {
                mem[0][i] = true;
            } else {
                break;
            }
        }
        for(int i=1; i <= s1.length(); i++) {
            for(int j=1; j <= s2.length(); j++) {
                mem[i][j] = (mem[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1)) || (mem[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return mem[s1.length()][s2.length()];
    }



    public boolean isInterleave_Recursion(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()){
            return false;
        }
        return isInterleave(s1, s2, s3, 0, 0);
    }


    private boolean isInterleave(String s1, String s2, String s3, int i, int j) {
        //System.out.println("i=" + i + ", j=" + j);
        if(i+j == s1.length() + s2.length()) {
            return true;
        }
        boolean result = false;
        if(i < s1.length() && s1.charAt(i) == s3.charAt(i+j)) {
            result = isInterleave(s1, s2, s3, i+1, j);
            //System.out.println("first: " + "i=" + i + ", j=" + j + ", result=" + result);
        }
        if(!result && j < s2.length() && s2.charAt(j) == s3.charAt(i+j)) {
            result = isInterleave(s1, s2, s3, i, j+1);
            //System.out.println("second: " + "i=" + i + ", j=" + j + ", result=" + result);
        }
        return result;
    }

}
