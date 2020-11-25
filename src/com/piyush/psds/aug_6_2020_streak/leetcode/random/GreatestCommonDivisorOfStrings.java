package com.piyush.psds.aug_6_2020_streak.leetcode.random;

//https://leetcode.com/problems/greatest-common-divisor-of-strings/submissions/
public class GreatestCommonDivisorOfStrings {

    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) { // make sure str1 is not shorter than str2.
            return gcdOfStrings(str2, str1);
        }else if (!str1.startsWith(str2)) { // shorter string is not common prefix.
            return "";
        }else if (str2.isEmpty()) { // gcd string found.
            return str1;
        }else { // cut off the common prefix part of str1.
            return gcdOfStrings(str1.substring(str2.length()), str2);
        }
    }

    public String gcdOfStrings_1(String str1, String str2) {
        int maxLength = Math.max(str1.length(), str2.length());
        int minLength = Math.min(str1.length(), str2.length());
        int maxPossibleLength = minLength;
        while (maxLength % maxPossibleLength != 0) {
            maxPossibleLength--;
        }
        for (int i = maxPossibleLength; i > 0; i--) {
            boolean present = check(str1, str2, i);
            if (present) {
                return str1.substring(0, i);
            }
        }
        return "";
    }

    private boolean check(String str1, String str2, int last) {
        String s1 = str1.substring(0, last);
        String s2 = str2.substring(0, last);
        // System.out.println(s1 + " " + s2);
        if (!s1.equals(s2)) {
            return false;
        }
        int firstEnd = last;
        while (firstEnd < str1.length()) {
            if (firstEnd + last > str1.length() || !str1.substring(firstEnd, firstEnd + last).equals(s1)) {
                return false;
            }
            firstEnd += last;
        }

        int secondEnd = last;
        while (secondEnd < str2.length()) {
            if (secondEnd + last > str2.length() || !str2.substring(secondEnd, secondEnd + last).equals(s1)) {
                return false;
            }
            secondEnd += last;
        }
        return true;
    }
}
