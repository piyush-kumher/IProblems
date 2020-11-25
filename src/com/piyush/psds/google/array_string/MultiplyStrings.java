package com.piyush.psds.google.array_string;

public class MultiplyStrings {

    public String multiply(String nums1, String nums2) {
        int m = nums1.length();
        int n = nums2.length();
        int[] pos = new int[m+n];
        for(int i=m-1; i >= 0; i--) {
            int k = i + n;
            for(int j=n-1; j >= 0; j--) {
                int sum = (nums1.charAt(i) - '0') * (nums2.charAt(j) - '0') + pos[k];
                pos[k--] = sum % 10;
                pos[k] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean firstNonZeroFound = false;
        for(int i=0; i < pos.length; i++) {
            if(pos[i] != 0 || firstNonZeroFound) {
                sb.append(pos[i]);
                if(!firstNonZeroFound) {
                    firstNonZeroFound = true;
                }
            }
        }
        String result = sb.toString();
        return result.length() == 0 ? "0" : result;
    }

}
