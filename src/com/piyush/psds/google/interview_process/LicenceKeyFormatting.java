package com.piyush.psds.google.interview_process;

// https://leetcode.com/problems/license-key-formatting/submissions/
public class LicenceKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=S.length()-1; i >= 0; i--) {
            if(S.charAt(i) != '-') {
                sb.append(Character.toUpperCase(S.charAt(i)));
                count++;
                if(count > 0 && i != 0 && count % K == 0) {
                    sb.append("-");
                }
            }
        }
        String result = sb.reverse().toString();
        return result.startsWith("-") ? result.substring(1) : result;
    }

}
