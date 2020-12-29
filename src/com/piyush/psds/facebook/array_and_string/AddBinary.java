package com.piyush.psds.facebook.array_and_string;

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        while(i >= 0 && j >= 0) {
            int sum = (a.charAt(i--)-'0') + (b.charAt(j--)-'0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        while(i >= 0) {
            int sum = (a.charAt(i--) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        while(j >= 0) {
            int sum = (b.charAt(j--) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }
        if(carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

}
