package com.piyush.psds.aug_6_2020_streak.leetcode.first;

import java.util.Stack;

public class DecodeString {

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        DecodeString d = new DecodeString();
        System.out.println(d.decodeString(s));
    }

    int i=0;

    // recursion
    public String decodeString_Rec(String s) {
        return doRecur(s);
    }

    private String doRecur(String s) {
        StringBuilder sb = new StringBuilder();
        while(i < s.length() && s.charAt(i) != ']') {
            if(!Character.isDigit(s.charAt(i))) {
                sb.append(s.charAt(i++));
            } else {
                int k = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    k = k * 10 + s.charAt(i++) - '0';
                }
                // remove [
                i++;
                String ds = doRecur(s);
                // remove ]
                i++;
                while(k-- > 0) {
                    sb.append(ds);
                }
            }
        }
        return sb.toString();
    }

    //"3[a2[c]]"

    public String decodeString(String s) {
        Stack<String> resultStack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (Character.isLetter(s.charAt(i))) {
                builder.append(s.charAt(i));
                i++;
            }
            else if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + s.charAt(i) - '0';
                    i++;
                }
                countStack.push(count);
            }
            else if (s.charAt(i) == '[') {
                resultStack.push(builder.toString());
                builder = new StringBuilder();
                i++;
            }
            else if (s.charAt(i) == ']') {
                int repetition = countStack.pop();
                String st = builder.toString();
                while (--repetition > 0) {
                    builder.append(st);
                }
                builder.insert(0, resultStack.pop());
                i++;
            }
        }
        return builder.toString();
    }

}
