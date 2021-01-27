package com.piyush.psds.facebook.random;

public class AddString {

    public static void main(String[] args) {
        AddString as = new AddString();
        System.out.println(as.addStrings("98", "9"));
    }

    public String addStrings(String num1, String num2) {
        StringBuilder ans = new StringBuilder();
        int carry = 0;
        int l1 = num1.length();
        int l2 = num2.length();
        for(int i = 0; i < Math.max(l1, l2); i++) {
            int sum = (l1-i-1 >= 0 ? Character.getNumericValue(num1.charAt(l1-i-1)) : 0) +
                    (l2-i-1 >= 0 ? Character.getNumericValue(num2.charAt(l2-i-1)) : 0) + carry;
            ans.append(sum % 10);
            carry = sum / 10;
        }
        if(carry > 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();
    }

}
