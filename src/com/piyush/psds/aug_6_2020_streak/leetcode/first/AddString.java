package com.piyush.psds.aug_6_2020_streak.leetcode.first;

public class AddString {

    public static void main(String[] args) {
        AddString a = new AddString();
        String num1 = "345";
        String num2 = "11675";
        System.out.println(a.addStrings(num1, num2));
    }

    public String addStrings(String num1, String num2) {
        String ans = "";
        int carry = 0;
        int i = 0;
        for (; i < Math.min(num1.length(), num2.length()); i++) {
            int sum = (num1.charAt(num1.length() - i - 1) - '0') + (num2.charAt(num2.length() - i - 1) - '0') + carry;
            ans = (sum % 10) + ans;
            carry = sum / 10;
        }
        if (num1.length() > num2.length()) {
            for (; i < num1.length(); i++) {
                int sum = (num1.charAt(num1.length() - i - 1) - '0') + carry;
                ans = (sum % 10) + ans;
                carry = sum / 10;
            }
        }
        if (num1.length() < num2.length()) {
            for (; i < num2.length(); i++) {
                int sum = (num2.charAt(num2.length() - i - 1) - '0') + carry;
                ans = (sum % 10) + ans;
                carry = sum / 10;
            }
        }
        if (carry > 0) {
            ans = carry + ans;
        }
        return ans;
    }

}
