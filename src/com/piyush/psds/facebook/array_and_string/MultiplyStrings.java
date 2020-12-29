package com.piyush.psds.facebook.array_and_string;

public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings m = new MultiplyStrings();
        System.out.println(m.multiply("999", "999"));
    }

    public String multiply(String num1, String num2) {
        int maxLength = num1.length() + num2.length() + 1;
        int[] result = new int[maxLength];
        for(int i=num2.length()-1; i >= 0 ; i--) {
            int carry = 0;
            int start = maxLength - (num2.length()-i);
            for(int j=num1.length()-1; j >= 0; j--) {
                int mul = (num1.charAt(j) - '0') * (num2.charAt(i) - '0') + carry + result[start];
                result[start--] = mul % 10;
                carry = mul / 10;
            }
            while(carry > 0) {
                result[start--] = carry % 10;
                carry = carry / 10;
            }
        }
        boolean foundStart = false;
        StringBuilder ans = new StringBuilder();
        for(int i=0; i < maxLength; i++) {
            if(!foundStart && result[i] == 0) {
                // do nothing
            } else {
                foundStart = true;
                ans.append(result[i]);
            }
        }
        return ans.toString();
    }
}
