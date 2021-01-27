package com.piyush.psds.google.array_string;

public class BackspaceStringCompare {

    public static void main(String[] args) {
        BackspaceStringCompare b = new BackspaceStringCompare();
        System.out.println(b.backspaceCompare("ab#c", "ad#c"));
    }

    public boolean backspaceCompare(String S, String T) {
        return getStringWithoutBackSpace(S).equals(getStringWithoutBackSpace(T));
    }

    private String getStringWithoutBackSpace(String S) {
        StringBuilder sb1 = new StringBuilder();
        int bcount = 0;
        for(int i=S.length()-1; i >= 0; i--) {
            if(S.charAt(i) == '#') {
                bcount++;
            } else if(bcount > 0) {
                bcount--;
            } else {
                sb1.append(S.charAt(i));
            }
        }
        return sb1.toString();
    }

    public boolean backspaceCompare1(String S, String T) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int bcount = 0;
        for(int i=S.length()-1; i >= 0; i--) {
            if(S.charAt(i) == '#') {
                bcount++;
            } else if(bcount > 0) {
                bcount--;
            } else {
                sb1.append(S.charAt(i));
            }
        }
        bcount = 0;
        for(int i=T.length()-1; i >= 0; i--) {
            if(T.charAt(i) == '#') {
                bcount++;
            } else if(bcount > 0) {
                bcount--;
            } else {
                sb2.append(T.charAt(i));
            }
        }
        return sb1.toString().equals(sb2.toString());
    }

}
