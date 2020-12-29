package com.piyush.psds.google.interview_process;

// https://leetcode.com/problems/license-key-formatting/submissions/
public class LicenceKeyFormatting {

    public static void main(String[] args) {
        LicenceKeyFormatting l = new LicenceKeyFormatting();
        System.out.println(l.licenseKeyFormatting("--a-a-a-a--", 2));
    }

    public String licenseKeyFormatting_1(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) != '-') {
                sb.append(Character.toUpperCase(S.charAt(i)));
                count++;
                if (count > 0 && i != 0 && count % K == 0) {
                    sb.append("-");
                }
            }
        }
        String result = sb.reverse().toString();
        return result.startsWith("-") ? result.substring(1) : result;
    }

    public String licenseKeyFormatting(String S, int K) {
        if (K == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = S.length() - 1;
        int curr = 0;
        while (index >= 0) {
            char ch = S.charAt(index);
            if (ch != '-') {
                ch = Character.toUpperCase(ch);
                sb.append(ch);
                curr++;
                if (curr % K == 0 && curr != 0) {
                    sb.append('-');
                }
            }
            index--;
        }
        return sb.reverse().toString();
    }

}
