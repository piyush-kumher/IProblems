package com.piyush.psds.google.trees_and_graph;

import java.util.Stack;

public class DecodeString {

    int index = 0;

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        while(index < s.length() && s.charAt(index) != ']') {
            if(!Character.isDigit(s.charAt(index))) {
                sb.append(s.charAt(index++));
            } else {
                int k = 0;
                while(index < s.length() && Character.isDigit(s.charAt(index))) {
                    k = k * 10 + s.charAt(index++) - '0';
                }
                index++;
                String decodedString = decodeString(s);
                index++;
                while(k-- > 0) {
                    sb.append(decodedString);
                }
            }
        }
        return new String(sb);
    }


    public String decodeString_stack(String s) {
        Stack<Integer> ns = new Stack<>();
        Stack<StringBuilder> ss = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int k = 0;
        for(char ch : s.toCharArray()) {
            if(Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if(ch == '[') {
                ns.push(k);
                k = 0;
                ss.push(sb);
                sb = new StringBuilder();
            } else if(ch == ']') {
                StringBuilder tempSb = ss.pop();
                int tempK = ns.pop();
                while(tempK-- > 0) {
                    tempSb.append(sb);
                }
                sb = tempSb;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

}
